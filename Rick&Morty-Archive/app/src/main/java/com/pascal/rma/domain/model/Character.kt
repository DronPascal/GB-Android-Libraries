package com.pascal.rma.domain.model

/**
 * Created by dronpascal on 05.10.2021.
 */
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val origin: String,
    val originId: Int,
    val location: String,
    val locationId: Int,
    val episodes: List<Episode>
) {
    data class Episode(
        val id: Int,
        val name: String
    )
}
