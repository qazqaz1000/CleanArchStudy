package com.nckim.domain.di

import com.nckim.domain.repository.LoginRepository
import com.nckim.domain.repository.MovieRepository
import com.nckim.domain.usecase.*
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

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(movieRepository: MovieRepository): GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetLocalMoviesUseCase(movieRepository: MovieRepository): GetLocalMoviesUseCase{
        return GetLocalMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetPagingMoviesUseCase(movieRepository: MovieRepository): GetPagingMoviesUseCase{
        return GetPagingMoviesUseCase(movieRepository)
    }
}