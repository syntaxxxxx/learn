package com.syntax.learn.screen.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.syntax.learn.R
import com.syntax.learn.screen.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private var mDelayHandler : Handler? = null
    private var splashScreenDelay : Long = 3000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()


        mDelayHandler = Handler().apply {
            postDelayed(mRunnable, splashScreenDelay)
        }

    }


    public override fun onDestroy() {

        mDelayHandler?.removeCallbacks(mRunnable)

        super.onDestroy()
    }
}
