package com.nckim.domain.usecase

import com.nckim.domain.repository.LoginRepository

class GetLoginUseCase constructor(private val repository: LoginRepository){
    fun excute(): Boolean = repository.autoLogin
}