package com.nckim.data.repository.movie.remote

import com.nckim.data.model.movie.MovieResponse
import io.reactivex.Single

interface  MovieRemoteDataSource {
    fun getSearchMovies(
        query: String,
        start: Int = 1
    ): Single<MovieResponse>
}