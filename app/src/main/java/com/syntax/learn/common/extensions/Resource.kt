package com.syntax.learn.common.extensions

import com.syntax.learn.LearnApp

fun Int.resString() = LearnApp.getContext().getString(this)