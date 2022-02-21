package com.nckim.cleanarchstudy.views.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nckim.domain.usecase.GetLoginUseCase
import com.nckim.domain.usecase.SetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getLoginUseCase: GetLoginUseCase) : ViewModel(){

//    @Inject
//    lateinit var setLoginUseCase: SetLoginUseCase

    private val _startMovieActivity : MutableLiveData<Unit> = MutableLiveData()
    private val _startLoginActivity : MutableLiveData<Unit> = MutableLiveData()

    val startMovieActivity : LiveData<Unit>
        get() {
            return _startMovieActivity
        }

    val startLoginActivity : LiveData<Unit> get() = _startLoginActivity

    fun doSplash(){
        if(getLoginUseCase.excute()){
            _startMovieActivity.value = Unit
        }else{
            _startLoginActivity.value = Unit
        }
//        setLoginUseCase.excute(false)

    }
}