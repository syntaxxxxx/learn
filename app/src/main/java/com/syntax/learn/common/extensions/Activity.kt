package com.syntax.learn.common.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.syntax.learn.common.Navigator
import com.syntax.learn.common.NavigatorHandler
import com.syntax.learn.common.NavigatorViewModel

fun AppCompatActivity.setupNavigator(owner: LifecycleOwner, viewModel: NavigatorViewModel) {
    viewModel.navigator.observe(owner, Observer {
        it.navigate(this)

        if (this is NavigatorHandler) {
            this.handleNavigation(it)
        }
    })
}

fun AppCompatActivity.navigate(navigator: Navigator) {
    navigator.navigate(this)

    if (this is NavigatorHandler) {
        this.handleNavigation(navigator)
    }
}

inline fun <reified T: ViewModel> AppCompatActivity.activityViewModel() = lazy {
    ViewModelProviders.of(this).get(T::class.java)
}