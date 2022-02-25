package com.nckim.data.repository.search.remote

import com.nckim.data.api.ApiInterface
import com.nckim.data.model.search.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): MovieRemoteDataSource{
    override fun getSearchMovies(query: String, start: Int): Single<MovieResponse> {
        return apiInterface.getSearchMovie(query, start)
    }
}