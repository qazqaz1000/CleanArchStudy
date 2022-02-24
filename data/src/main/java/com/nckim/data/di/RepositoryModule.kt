package com.nckim.data.di

import com.nckim.data.repository.login.LoginRepositoryImpl
import com.nckim.data.repository.login.local.LoginLocalDataSource
import com.nckim.data.repository.search.MovieRepositoryImpl
import com.nckim.data.repository.search.local.MovieLocalDataSource
import com.nckim.data.repository.search.remote.MovieRemoteDataSource
import com.nckim.domain.repository.LoginRepository
import com.nckim.domain.repository.MovieRepository
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
    fun provideMovieRepository(movieLocalDataSource: MovieLocalDataSource,
                               movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository {
        return LoginRepositoryImpl(loginLocalDataSource)
    }

}