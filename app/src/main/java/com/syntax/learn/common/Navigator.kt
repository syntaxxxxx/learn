package com.syntax.learn.common

import android.content.Context

abstract class Navigator {
    open fun navigate(context: Context) {}
}