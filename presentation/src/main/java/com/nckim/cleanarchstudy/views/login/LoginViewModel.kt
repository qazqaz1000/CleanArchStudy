package com.nckim.cleanarchstudy.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nckim.domain.usecase.SetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val setLoginUseCase: SetLoginUseCase): ViewModel(){
    val _id : MutableLiveData<String> = MutableLiveData("가")
    val _pw : MutableLiveData<String> = MutableLiveData("나")

    private val _isIDEmpty : MutableLiveData<Unit> = MutableLiveData()
    private val _isPWEmpty : MutableLiveData<Unit> = MutableLiveData()
    private val _isInvalid : MutableLiveData<Unit> = MutableLiveData()
    private val _isSuccess : MutableLiveData<Unit> = MutableLiveData()

    val isIDEmpty : LiveData<Unit> get() = _isIDEmpty
    val isPWEmpty : LiveData<Unit> get() = _isPWEmpty
    val isInvalid : LiveData<Unit> get() = _isInvalid
    val isSuccess : LiveData<Unit> get() = _isSuccess



    fun onClickLogin(){
        Log.e("NCTEST", "id : ${_id.value.toString()} , pw : ${_pw.value.toString()}")
        val id = _id.value.toString()
        val pw = _pw.value.toString()
        if(id.isEmpty()){
            _isIDEmpty.value = Unit
        }else if(pw.isEmpty()){
            _isPWEmpty.value = Unit
        }else if(id == USER_ID && id == USER_PW){
            setLoginUseCase.excute(true)
            _isSuccess.value = Unit
        }else{
            _isInvalid.value = Unit
        }
    }

    companion object{
        private const val USER_ID = "nc"
        private const val USER_PW = "kim"
    }

}