package com.nckim.data.di

import android.content.Context
import androidx.room.Room
import com.nckim.data.db.MovieDao
import com.nckim.data.db.MovieDatabase
import com.nckim.data.repository.login.PreferenceManager
import com.nckim.data.repository.login.local.LoginLocalDataSource
import com.nckim.data.repository.login.local.LoginLocalDataSourceImpl
import com.nckim.data.repository.movie.local.MovieLocalDataSource
import com.nckim.data.repository.movie.local.MovieLocalDataSourceImpl
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
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDao)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase{
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "Movie.db"
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao{
        return movieDatabase.movieDao()
    }


    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager{
        return PreferenceManager(context)
    }
}