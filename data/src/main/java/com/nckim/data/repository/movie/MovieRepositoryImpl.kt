package com.nckim.data.repository.movie

import com.nckim.data.mapper.mapperToMovie
import com.nckim.data.repository.movie.local.MovieLocalDataSource
import com.nckim.data.repository.movie.remote.MovieRemoteDataSource
import com.nckim.data.utils.LAST_PAGE
import com.nckim.data.utils.NO_DATA_FROM_LOCAL_DB
import com.nckim.domain.model.movie.Movie
import com.nckim.domain.repository.MovieRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
): MovieRepository{

    override fun getSearchMovies(query: String): Flowable<List<Movie>> {
        return movieLocalDataSource.getSearchMovies(query)
            .onErrorReturn { listOf() }
            .flatMapPublisher { localMovies ->
                if(localMovies.isEmpty()){
                    getRemoteSearchMovies(query)
                        .toFlowable()
                        .onErrorReturn { listOf() }
                }else{
                    val local = Single.just(mapperToMovie(localMovies))
                    val remote = getRemoteSearchMovies(query)
                        .onErrorResumeNext(local)
                    Single.concat(local, remote)
                }
            }
    }

    override fun getLocalSearchMovies(query: String): Flowable<List<Movie>> {
        return movieLocalDataSource.getSearchMovies(query)
            .onErrorReturn { listOf() }
            .flatMapPublisher { cachedMovies ->
                if (cachedMovies.isEmpty()){
                    Flowable.error(IllegalStateException(NO_DATA_FROM_LOCAL_DB))
                }else {
                    Flowable.just(mapperToMovie(cachedMovies))
                }

            }
    }

    override fun getRemoteSearchMovies(query: String): Single<List<Movie>> {
        return movieRemoteDataSource.getSearchMovies(query)
            .flatMap {
                movieLocalDataSource.insertMovies(it.movies)
                    .andThen(Single.just(mapperToMovie(it.movies)))
            }
    }

    override fun getPagingMovies(query: String, offset: Int): Single<List<Movie>> {
        return movieRemoteDataSource.getSearchMovies(query, offset)
            .flatMap {
                if (it.movies.isEmpty()){
                    Single.error(IllegalStateException(LAST_PAGE))
                }else{
                    if(offset != it.start){
                        Single.error(IllegalStateException(LAST_PAGE))
                    }else{
                        movieLocalDataSource.insertMovies(it.movies)
                            .andThen(Single.just(mapperToMovie(it.movies)))
                    }
                }
            }
    }
}