package com.pascal.rma.data.remote.retrofit.episode

import com.pascal.rma.data.remote.retrofit.episode.model.ApiEpisode
import com.pascal.rma.domain.model.Episode
import com.pascal.rma.util.StringUtil.unitId

/**
 * Created by dronpascal on 12.10.2021.
 */
object ApiEpisodeMapper {

    fun ApiEpisode.toEpisode(): Episode {
        return with(this) {
            Episode(
                id = id,
                name = name,
                episode = episode,
                air_date = air_date,
                characterIds = characters.ifEmpty { null }?.mapNotNull { it.unitId() }
            )
        }
    }

}