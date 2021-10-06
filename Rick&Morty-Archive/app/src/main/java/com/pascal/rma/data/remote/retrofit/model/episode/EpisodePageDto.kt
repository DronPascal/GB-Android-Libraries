package com.pascal.rma.data.remote.retrofit.model.episode

data class EpisodePageDto(
    val info: InfoDto,
    val results: List<EpisodeDto>
)