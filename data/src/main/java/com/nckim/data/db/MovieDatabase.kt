package com.nckim.data.db

import androidx.room.Database
import com.nckim.data.model.search.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase {
    abstract fun movieDao(): MovieDao
}