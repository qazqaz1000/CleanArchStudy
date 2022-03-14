package com.nckim.domain.usecase

import com.nckim.domain.model.movie.Movie
import com.nckim.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPagingMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    fun excute(
        query: String,
        offset: Int
    ): Single<List<Movie>> = repository.getPagingMovies(query, offset)
}