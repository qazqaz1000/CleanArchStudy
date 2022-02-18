package com.nckim.cleanarchstudy.views.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.nckim.cleanarchstudy.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SplashActivity : AppCompatActivity() {
    init {
        Log.e("NCTEST", "SplashActivity init")
    }
    private val viewModel:SplashViewModel by viewModels()
    init {
        Log.e("NCTEST", "SplashActivity init2")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.e("NCTEST", "SplashActivity init3")
        viewModel.doSplash()

        Log.e("NCTEST", "SplashActivity init4")
    }
}