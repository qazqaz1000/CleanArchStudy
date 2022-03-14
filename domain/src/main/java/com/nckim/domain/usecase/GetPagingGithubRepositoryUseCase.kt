package com.nckim.domain.usecase

import com.nckim.domain.model.github.GithubRepositoryModel
import com.nckim.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPagingGithubRepositoryUseCase @Inject constructor(private val githubRepository: GithubRepository){
    fun execute(
        q: String,
        offset: Int
    ): Single<List<GithubRepositoryModel>> = githubRepository.getPagingGithubRepo(q, offset)
}