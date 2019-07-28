package com.syntax.learn.screen.register

import android.content.Context
import android.content.Intent
import com.syntax.learn.common.Navigator
import com.syntax.learn.screen.login.LoginFragment

object RegisterNavigator  {
    class btnRegister : Navigator() {
        override fun navigate (context: Context) {
            context.startActivity(Intent(context, LoginFragment::class.java))
        }
    }

}