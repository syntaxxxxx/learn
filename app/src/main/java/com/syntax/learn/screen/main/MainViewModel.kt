package com.syntax.learn.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _counter = MutableLiveData<Int>()
    val counter = _counter as LiveData<Int>

    init {
        _counter.value = 0
    }

    fun increaseCounter() {
        _counter.value = (_counter.value ?: 0) + 1
    }
}