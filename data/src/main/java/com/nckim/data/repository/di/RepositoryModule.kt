package com.nckim.data.repository.di

import com.nckim.data.repository.login.LoginRepositoryImpl
import com.nckim.data.repository.login.local.LoginLocalDataSource
import com.nckim.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository{
        return LoginRepositoryImpl(loginLocalDataSource)
    }
}