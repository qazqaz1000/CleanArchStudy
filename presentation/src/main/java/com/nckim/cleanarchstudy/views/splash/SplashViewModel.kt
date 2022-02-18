package com.nckim.cleanarchstudy.views.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nckim.domain.usecase.GetLoginUseCase
import com.nckim.domain.usecase.SetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getLoginUseCase: GetLoginUseCase) : ViewModel(){

    @Inject
    lateinit var setLoginUseCase: SetLoginUseCase

    init {
        Log.e("NCTEST", "SplashViewModel init")
    }

    fun doSplash(){
        Log.e("NCTEST", "doSplash " + getLoginUseCase.excute())
        setLoginUseCase.excute(true)
        Log.e("NCTEST", "doSplash " + getLoginUseCase.excute())
        setLoginUseCase.excute(false)
        Log.e("NCTEST", "doSplash " + getLoginUseCase.excute())
//        if(getLoginUseCase.excute()){
//            Log.e("NCTEST", "true")
//        }else{
//            Log.e("NCTEST", "false")
//        }
    }
}