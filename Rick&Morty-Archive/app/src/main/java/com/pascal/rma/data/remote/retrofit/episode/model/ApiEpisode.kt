package com.pascal.rma.data.remote.retrofit.episode.model

data class ApiEpisode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)