package com.pascal.rma.data.local.cache.room.episode

import com.pascal.rma.data.remote.retrofit.episode.model.ApiEpisode
import com.pascal.rma.domain.model.Episode
import com.pascal.rma.util.StringUtil.unitId

/**
 * Created by dronpascal on 12.10.2021.
 */
object RoomEpisodeMappers {

    fun Episode.toRoomEpisode(): RoomEpisode {
        return with(this) {
            RoomEpisode(
                id = id,
                name = name,
                episode = episode,
                air_date = air_date,
                characterIds = characterIds?.joinToString(separator = ","),
            )
        }
    }

    fun RoomEpisode.toEpisode(): Episode {
        return with(this) {
            Episode(
                id = id,
                name = name,
                episode = episode,
                air_date = air_date,
                characterIds = characterIds?.split(",")?.map { it.toInt() },
            )
        }
    }

    fun ApiEpisode.toRoomEpisode(): RoomEpisode {
        return with(this) {
            RoomEpisode(
                id = id,
                name = name,
                episode = episode,
                air_date = air_date,
                characterIds = characters.ifEmpty { null }
                    ?.mapNotNull { it.unitId() }
                    ?.joinToString(separator = ",")
            )
        }
    }

}