package com.pascal.rma.data.remote.retrofit.episode.model

data class EpisodePageDto(
    val info: InfoDto,
    val results: List<EpisodeDto>
)