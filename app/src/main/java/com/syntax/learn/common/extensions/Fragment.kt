package com.syntax.learn.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.syntax.learn.common.Navigator
import com.syntax.learn.common.NavigatorHandler
import com.syntax.learn.common.NavigatorViewModel

fun Fragment.setupNavigator(
    owner: LifecycleOwner,
    viewModel: NavigatorViewModel,
    useActivityHandler: Boolean = false
) {
    viewModel.navigator.observe(owner, Observer {
        it.navigate(requireContext())

        if (this is NavigatorHandler) {
            this.handleNavigation(it)
        }

        val handlerActivity = activity as? NavigatorHandler
        if (useActivityHandler && handlerActivity != null) {
            handlerActivity.handleNavigation(it)
        }
    })
}

fun Fragment.navigate(navigator: Navigator, useActivityHandler: Boolean = false) {
    navigator.navigate(requireContext())

    if (this is NavigatorHandler) {
        this.handleNavigation(navigator)
    }

    val handlerActivity = activity as?NavigatorHandler
    if (useActivityHandler && handlerActivity != null){
        handlerActivity.handleNavigation(navigator)
    }
}

inline fun <reified T: ViewModel> Fragment.fragmentViewModel() = lazy {
    ViewModelProviders.of(this).get(T::class.java)
}

inline fun <reified T: ViewModel> Fragment.activityViewModel() = lazy {
    ViewModelProviders.of(requireActivity()).get(T::class.java)
}