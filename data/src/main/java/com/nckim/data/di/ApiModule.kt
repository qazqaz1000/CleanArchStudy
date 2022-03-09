package com.nckim.data.di

import com.nckim.data.api.GithubApiInterface
import com.nckim.data.api.NaverApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideNaverApiInterface(): NaverApiInterface{
        return NaverApiInterface.createNaverApi()
    }

    @Provides
    @Singleton
    fun provideGithubApiInterface(): GithubApiInterface{
        return GithubApiInterface.createGithubApi()
    }

}