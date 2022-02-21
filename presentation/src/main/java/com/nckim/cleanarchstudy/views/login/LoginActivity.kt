package com.nckim.cleanarchstudy.views.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nckim.cleanarchstudy.R
import com.nckim.cleanarchstudy.base.BaseActivity
import com.nckim.cleanarchstudy.databinding.ActivityLoginBinding
import com.nckim.cleanarchstudy.views.search.MovieSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login){
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initCallback()
    }

    fun initCallback(){
        with(viewModel){
            viewModel.isIDEmpty.observe(this@LoginActivity, Observer { errorIDEmpty() })
            viewModel.isPWEmpty.observe(this@LoginActivity, Observer { errorPWEmpty() })
            viewModel.isInvalid.observe(this@LoginActivity, Observer { errorInvalid() })
            viewModel.isSuccess.observe(this@LoginActivity, Observer { success() })
        }
    }

    private fun errorIDEmpty(){
        binding.editId.error = getString(R.string.activity_login_id)
    }

    private fun errorPWEmpty(){
        binding.editPw.error = getString(R.string.activity_login_pw)
    }

    private fun errorInvalid(){
        showToast(getString(R.string.activity_login_false))
    }

    private fun success(){
        finish()
        showToast(getString(R.string.activity_login_true))
        startActivity(Intent(this, MovieSearchActivity::class.java))
    }


}