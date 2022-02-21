package com.nckim.domain.di

import android.util.Log
import com.nckim.domain.repository.LoginRepository
import com.nckim.domain.usecase.GetLoginUseCase
import com.nckim.domain.usecase.SetLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetLoginUseCase(loginRepository: LoginRepository): GetLoginUseCase{
        return GetLoginUseCase(loginRepository)
    }

    @Provides
    @Singleton
    fun provideSetLoginUseCase(loginRepository: LoginRepository): SetLoginUseCase{
        return SetLoginUseCase(loginRepository)
    }
}