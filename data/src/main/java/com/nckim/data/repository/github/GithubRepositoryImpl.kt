package com.nckim.data.repository.github

import com.nckim.data.mapper.mapperToGithubRepository
import com.nckim.data.mapper.mapperToMovie
import com.nckim.data.repository.github.remote.GithubRemoteDataSource
import com.nckim.data.utils.LAST_PAGE
import com.nckim.domain.model.github.GithubRepositoryModel
import com.nckim.domain.model.movie.Movie
import com.nckim.domain.repository.GithubRepository
import io.reactivex.Flowable
import io.reactivex.Single

class GithubRepositoryImpl constructor(private val githubRemoteDataSource: GithubRemoteDataSource): GithubRepository {
    override fun getGithubRepository(q: String): Single<List<GithubRepositoryModel>> {
        return githubRemoteDataSource.getGithubRepositories(q).flatMap {
            Single.just(mapperToGithubRepository(it.items))
        }
    }

    override fun getPagingGithubRepo(q: String, offset: Int): Single<List<GithubRepositoryModel>> {
    return githubRemoteDataSource.getGithubRepositories(q, offset).flatMap {
        if (it.items.isEmpty()){
            Single.error(IllegalStateException(LAST_PAGE))
        }else{
            if(offset > it.totalCount){
                Single.error(IllegalStateException(LAST_PAGE))
            }else{
                Single.just(mapperToGithubRepository(it.items))
            }
        }
    }
    }
}