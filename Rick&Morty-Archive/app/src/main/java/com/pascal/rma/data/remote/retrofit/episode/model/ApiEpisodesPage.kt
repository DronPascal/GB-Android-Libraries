package com.pascal.rma.data.remote.retrofit.episode.model

data class ApiEpisodesPage(
    val info: ApiPageInfo,
    val results: List<ApiEpisode>
)