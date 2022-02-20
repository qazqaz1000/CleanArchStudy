package com.nckim.domain.usecase

import android.util.Log
import com.nckim.domain.repository.LoginRepository
import javax.inject.Inject

class SetLoginUseCase @Inject constructor(private val repository: LoginRepository){
    fun excute(success: Boolean){
        repository.autoLogin = success
    }
}