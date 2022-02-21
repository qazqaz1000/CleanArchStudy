package com.nckim.domain.usecase

import com.nckim.domain.repository.LoginRepository
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val repository: LoginRepository){
    fun excute(): Boolean = repository.autoLogin
}
