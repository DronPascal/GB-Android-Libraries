package com.pascal.rma.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by dronpascal on 05.10.2021.
 */
@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val origin: String,
    val originId: Int?,
    val location: String,
    val locationId: Int?,
    val episodeIds: List<Int>?
) : Parcelable {

    // 🟢 Alive - Human
    fun getStatusAndSpecies(): String? {
        if (status.isEmpty() && species.isEmpty()) return null
        val led = when (status) {
            "Alive" -> "🟢 "
            "Dead" -> "🔴 "
            "unknown" -> ""
            else -> ""
        }
        val state = if (status == "unknown") "" else status
        val race = if (species == "unknown") "" else species
        val separator = if (state.isEmpty() || race.isEmpty()) "" else " - "
        return "$led$state$separator$race"
    }

}

