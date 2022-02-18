package com.nckim.data.repository.login

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager (context: Context){
    private val autoLoginPreference: SharedPreferences = context.getSharedPreferences(MOVIE_SEARCH_APP,Context.MODE_PRIVATE)

    var autoLogin: Boolean
        get() {
            return autoLoginPreference.getBoolean(AUTO_LOGIN_KEY, false)
        }
        set(value) {
            val editor = autoLoginPreference.edit()
            editor.putBoolean(AUTO_LOGIN_KEY, value)
            editor.apply()
        }


    companion object{
        private const val MOVIE_SEARCH_APP = "MOVIE_SEARCH_APP"
        private const val AUTO_LOGIN_KEY = "AUTO_LOGIN_KEY"
    }
}