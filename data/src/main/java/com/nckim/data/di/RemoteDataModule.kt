package com.nckim.data.di

import com.nckim.data.api.ApiInterface
import com.nckim.data.repository.search.remote.MovieRemoteDataSource
import com.nckim.data.repository.search.remote.MovieRemoteDataSourceImpl
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
    fun provideMovieRemoteDataSource(apiInterface: ApiInterface): MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(apiInterface)
    }
}