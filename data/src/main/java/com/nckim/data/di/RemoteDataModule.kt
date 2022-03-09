package com.nckim.data.di

import com.nckim.data.api.NaverApiInterface
import com.nckim.data.repository.movie.remote.MovieRemoteDataSource
import com.nckim.data.repository.movie.remote.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(naverApiInterface: NaverApiInterface): MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(naverApiInterface)
    }
}