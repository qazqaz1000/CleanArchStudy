package com.nckim.cleanarchstudy.views.splash

import android.content.Intent
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.views.IS_HOME_ACTIVITY
import com.nckim.cleanarchstudy.views.home.HomeActivity
import com.nckim.cleanarchstudy.views.login.LoginActivity
import com.nckim.cleanarchstudy.views.search.MovieSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SplashActivity : AppCompatActivity() {

    private val viewModel:SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initCallback()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            viewModel.doSplash()
        }, 1000)


    }

    private fun initCallback(){
        with(viewModel){
            startMovieActivity.observe(this@SplashActivity, Observer { startMovie() })
            startLoginActivity.observe(this@SplashActivity, Observer { startLogin() })
        }
    }

    private fun startMovie(){
        showToast(getString(R.string.activity_login_auto_true))
        finish()
        if(IS_HOME_ACTIVITY){
            startActivity(Intent(this, HomeActivity::class.java))
        }else{
            startActivity(Intent(this, MovieSearchActivity::class.java))
        }
    }

    private fun startLogin(){
        showToast(getString(R.string.activity_login_auto_false))
        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun showToast(msg : String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}
