package com.nckim.data.di

import com.nckim.data.api.GithubApiInterface
import com.nckim.data.repository.github.GithubRepositoryImpl
import com.nckim.data.repository.github.remote.GithubRemoteDataSource
import com.nckim.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GithubRepositoryModule {
    @Provides
    @Singleton
    fun provideGithubRepositoryModule(githubRemoteDataSource: GithubRemoteDataSource): GithubRepository{
        return GithubRepositoryImpl(githubRemoteDataSource);
    }
}