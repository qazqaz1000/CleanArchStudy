package com.nckim.data.repository.login

import com.nckim.data.repository.login.local.LoginLocalDataSource
import com.nckim.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginLocalDataSource: LoginLocalDataSource) :
    LoginRepository {
    override var autoLogin: Boolean
        get() = loginLocalDataSource.autoLogin
        set(value) {
            loginLocalDataSource.autoLogin = value
        }

}