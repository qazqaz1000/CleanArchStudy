package com.nckim.cleanarchstudy.views.login

import androidx.lifecycle.ViewModel
import com.nckim.domain.usecase.SetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val setLoginUseCase: SetLoginUseCase): ViewModel(){


}