package com.nckim.data.repository.login.local

import com.nckim.data.repository.login.PreferenceManager
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(private val preferenceManager: PreferenceManager) : LoginLocalDataSource{
    override var autoLogin: Boolean
        get() = preferenceManager.autoLogin
        set(value) {
            preferenceManager.autoLogin = value
        }

}