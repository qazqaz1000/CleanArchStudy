package com.nckim.data.repository.github.remote

import com.nckim.data.model.github.GithubResponse
import io.reactivex.Single

interface GithubRemoteDataSource {
    fun getGithubRepositories(
        q: String,
        offset: Int = 1
    ): Single<GithubResponse>
}