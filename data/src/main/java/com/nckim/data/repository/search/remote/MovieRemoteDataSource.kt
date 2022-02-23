package com.nckim.data.repository.search.remote

import com.nckim.data.model.search.MovieResponse
import io.reactivex.Single

interface  MovieRemoteDataSource {
    fun getSearchMovies(
        query: String,
        start: Int = 1
    ): Single<MovieResponse>
}