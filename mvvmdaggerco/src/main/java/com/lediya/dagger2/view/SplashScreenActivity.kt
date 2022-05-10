package com.lediya.dagger2.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.lediya.dagger2.R
import com.lediya.dagger2.utils.Constants
import com.lediya.dagger2.view.ListScreenActivity

class SplashScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, ListScreenActivity::class.java))
            finish()
        }, Constants.SPLASH_TIME_OUT)
    }
}