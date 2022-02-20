package com.nckim.cleanarchstudy.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutId:Int) : AppCompatActivity(){
    lateinit var binding: B
    private val compositeDisposable = CompositeDisposable()
}