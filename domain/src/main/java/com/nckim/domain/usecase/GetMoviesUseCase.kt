package com.nckim.domain.usecase

import com.nckim.domain.model.search.Movie
import com.nckim.domain.repository.MovieRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository){
    fun excute(
        query: String
    ): Flowable<List<Movie>> = repository.getSearchMovies(query)
}