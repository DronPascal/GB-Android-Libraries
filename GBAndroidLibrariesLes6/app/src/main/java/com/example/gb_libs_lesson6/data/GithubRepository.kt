package com.example.gb_libs_lesson6.data

import com.google.gson.annotations.Expose

data class GithubRepository(
    @Expose
    val id: String,
    @Expose
    val name: String,
    @Expose
    val forksCount: Int,
)
