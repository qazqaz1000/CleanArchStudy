package com.nckim.cleanarchstudy.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(){
    protected val compositeDisposable = CompositeDisposable()
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() {
            return _isLoading
        }

    fun showProgress(){
        _isLoading.value = true
    }

    fun hideProgress(){
        _isLoading.value = false
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    open fun onEndlessScroll(){

    }
}