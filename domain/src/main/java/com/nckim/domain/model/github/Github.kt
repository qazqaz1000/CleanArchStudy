package com.nckim.domain.model.github


data class GithubModel(
    val totalCount : Int,
    val items: List<GithubRepositoryModel>

)

data class GithubRepositoryModel(
    val id: Long,
    val fullName: String?,
    val htmlUrl: String?,
    val description: String?,
    val stargazersCount: Int,
    val avatarUrl: String?
)

//data class GithubRepositoryOwnerModel(
//    val avatarUrl: String
//)