package com.example.gb_libs_lesson6.data.remote.model

import com.example.gb_libs_lesson6.domain.model.GithubRepo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubRepoDto(
    @Expose
    val id: String,
    @Expose
    val name: String,
    @Expose
    @SerializedName("forks_count")
    val forksCount: Int,
)

fun GithubRepoDto.toGithubRepo(): GithubRepo {
    return GithubRepo(
        id = id,
        name = name,
        forksCount = forksCount
    )
}

fun List<GithubRepoDto>.toGithubRepos(): List<GithubRepo> {
    return map { it.toGithubRepo() }
}