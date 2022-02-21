package com.nckim.data.repository.di

import android.content.Context
import com.nckim.data.repository.login.PreferenceManager
import com.nckim.data.repository.login.local.LoginLocalDataSource
import com.nckim.data.repository.login.local.LoginLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLoginLocalDataSource(preferenceManager: PreferenceManager): LoginLocalDataSource{
        return LoginLocalDataSourceImpl(preferenceManager)
    }


    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager{
        return PreferenceManager(context)
    }
}