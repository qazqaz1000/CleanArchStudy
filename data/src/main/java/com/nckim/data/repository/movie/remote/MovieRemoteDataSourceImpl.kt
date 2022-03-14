package com.nckim.data.repository.movie.remote

import com.nckim.data.api.NaverApiInterface
import com.nckim.data.model.movie.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val naverApiInterface: NaverApiInterface): MovieRemoteDataSource{
    override fun getSearchMovies(query: String, start: Int): Single<MovieResponse> {
        return naverApiInterface.getSearchMovie(query, start)
    }
}