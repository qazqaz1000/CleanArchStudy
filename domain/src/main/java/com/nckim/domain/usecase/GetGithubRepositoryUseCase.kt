package com.nckim.domain.usecase

import com.nckim.domain.model.github.GithubRepositoryModel
import com.nckim.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

class GetGithubRepositoryUseCase @Inject constructor(private val githubRepository: GithubRepository){
    fun execute(q: String): Single<List<GithubRepositoryModel>> = githubRepository.getGithubRepository(q)
}