package com.nckim.domain.repository

import com.nckim.domain.model.github.GithubRepositoryModel
import io.reactivex.Single

interface GithubRepository {
    fun getGithubRepository(
        q: String
    ): Single<List<GithubRepositoryModel>>

    fun getPagingGithubRepo(
        q: String,
        offset: Int
    ): Single<List<GithubRepositoryModel>>
}