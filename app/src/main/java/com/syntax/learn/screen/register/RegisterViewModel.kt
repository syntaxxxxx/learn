package com.syntax.learn.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syntax.learn.common.Load
import com.syntax.learn.common.NavigatorViewModel
import com.syntax.learn.data.ApiService
import com.syntax.learn.data.UserPrefs
import com.syntax.learn.data.model.RegisterRequest
import com.syntax.learn.data.model.RegisterResponse
import com.syntax.learn.screen.main.MainNavigator
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel : NavigatorViewModel() {
    private val service = ApiService.create()
    private val _registerLoad = MutableLiveData<Load<RegisterResponse>>()
    val registerLoad = _registerLoad as LiveData<Load<RegisterResponse>>

    init {
        _registerLoad.value = Load.Uninitialized
    }

    fun doRegister(email: String, username: String, password1: String, password2: String) = viewModelScope.launch {
        _registerLoad.value = Load.Loading
        try {
            val response = service.register(RegisterRequest(email, username, password1, password2))
            UserPrefs.isRegister = true
            _registerLoad.value = Load.Success(response)

            navigate(MainNavigator.Profile())
        } catch (e: HttpException){
            _registerLoad.value = Load.Fail(e)
        }
    }
}