package com.syntax.learn

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

class LearnApp: Application() {
    override fun onCreate() {
        super.onCreate()
        mContext = WeakReference(applicationContext)
    }

    companion object {
        private var mContext: WeakReference<Context>? = null

        fun getContext(): Context {
            return mContext?.get() ?: throw Error("No Context")
        }
    }
}