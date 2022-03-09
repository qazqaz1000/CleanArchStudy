package com.nckim.data.repository.github.remote

import com.nckim.data.api.GithubApiInterface
import com.nckim.data.model.github.GithubResponse
import io.reactivex.Single
import javax.inject.Inject

class GithubRemoteDataSourceImpl @Inject constructor(private val githubApiInterface: GithubApiInterface) : GithubRemoteDataSource {
    override fun getGithubRepositories(q: String, offset: Int): Single<GithubResponse> {
        return githubApiInterface.getGithubRepository(q, offset)
    }
}