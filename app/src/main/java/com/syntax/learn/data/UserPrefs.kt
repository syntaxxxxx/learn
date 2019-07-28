package com.syntax.learn.data

import android.preference.PreferenceManager
import com.syntax.learn.LearnApp
import com.syntax.learn.common.utils.boolean
import com.syntax.learn.common.utils.stringNullable

object UserPrefs {
    private val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(LearnApp.getContext())
    }

    var isLogin by prefs.boolean("isLogin")
    var accessToken by prefs.stringNullable("accessToken")
    var refreshToken by prefs.stringNullable("refreshToken")

    var isRegister by prefs.boolean("isRegister")
}