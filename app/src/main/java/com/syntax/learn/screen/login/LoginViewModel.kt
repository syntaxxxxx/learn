package com.syntax.learn.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syntax.learn.common.Load
import com.syntax.learn.common.NavigatorViewModel
import com.syntax.learn.data.UserPrefs
import com.syntax.learn.data.ApiService
import com.syntax.learn.data.model.LoginRequest
import com.syntax.learn.data.model.LoginResponse
import com.syntax.learn.screen.main.MainNavigator
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel: NavigatorViewModel() {
    private val service = ApiService.create()


    private val _loginLoad = MutableLiveData<Load<LoginResponse>>()
    val loginLoad = _loginLoad as LiveData<Load<LoginResponse>>

    init {
        _loginLoad.value = Load.Uninitialized
    }

    fun doLogin(username: String, password: String) = viewModelScope.launch {
        _loginLoad.value = Load.Loading
        try {
            val response = service.login(LoginRequest(username, password))
            UserPrefs.isLogin = true
            UserPrefs.accessToken = response.access
            UserPrefs.refreshToken = response.refresh
            _loginLoad.value = Load.Success(response)
            navigate(MainNavigator.Profile())
        } catch (e: HttpException) {
            _loginLoad.value = Load.Fail(e)
        }
    }
}