package com.nckim.cleanarchstudy.views.home.github

import com.nckim.cleanarchstudy.base.BaseViewModel
import com.nckim.domain.usecase.GetGithubRepositoryUseCase
import com.nckim.domain.usecase.GetPagingGithubRepositoryUseCase
import javax.inject.Inject

class GithubSearchViewModel @Inject constructor(
    private val getGithubRepositoryUseCase: GetGithubRepositoryUseCase,
    private val getPagingGithubRepositoryUseCase: GetPagingGithubRepositoryUseCase
): BaseViewModel(){

//    fun get

}