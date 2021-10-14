package com.pascal.rma.domain.model

/**
 * Created by dronpascal on 05.10.2021.
 */
data class Episode(
    val id: Int,
    val name: String,
    val episode: String,
    val air_date: String,
    val characterIds: List<Int>?,
)
