package com.nckim.data.mapper

import com.nckim.data.model.github.GithubRepository
import com.nckim.data.model.github.GithubResponse
import com.nckim.data.model.movie.MovieEntity
import com.nckim.domain.model.github.GithubModel
import com.nckim.domain.model.github.GithubRepositoryModel
import com.nckim.domain.model.movie.Movie

fun mapperToMovie(movies: List<MovieEntity>): List<Movie> {
    return movies.toList().map {
        Movie(
            it.actor,
            it.director,
            it.image,
            it.link,
            it.pubDate,
            it.subtitle,
            it.title,
            it.userRating
        )
    }
}


fun mapperToGithubRepository(githubRepositories: List<GithubRepository>): List<GithubRepositoryModel> {
    return githubRepositories.toList().map {
        GithubRepositoryModel(
            it.id,
            it.fullName,
            it.htmlUrl,
            it.description,
            it.stargazersCount,
            it.owner.avatarUrl
        )
    }
}

