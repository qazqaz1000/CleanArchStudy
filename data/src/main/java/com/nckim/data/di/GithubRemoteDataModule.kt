package com.nckim.data.di

import com.nckim.data.api.GithubApiInterface
import com.nckim.data.repository.github.remote.GithubRemoteDataSource
import com.nckim.data.repository.github.remote.GithubRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GithubRemoteDataModule {
    @Singleton
    @Provides
    fun provideGithubRemoteDataSource(githubApiInterface: GithubApiInterface): GithubRemoteDataSource{
        return GithubRemoteDataSourceImpl(githubApiInterface)
    }
}