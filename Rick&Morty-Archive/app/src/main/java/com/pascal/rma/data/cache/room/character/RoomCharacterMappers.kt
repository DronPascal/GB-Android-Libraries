package com.pascal.rma.data.cache.room.character

import com.pascal.rma.data.remote.retrofit.character.model.ApiCharacter
import com.pascal.rma.domain.model.Character
import com.pascal.rma.util.unitId

/**
 * Created by dronpascal on 12.10.2021.
 */
object RoomCharacterMappers {

    fun Character.toRoomCharacter(): RoomCharacter {
        return with(this) {
            RoomCharacter(
                id = id,
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                image = image,
                origin = origin,
                originId = originId,
                location = location,
                locationId = locationId
            )
        }
    }

    fun RoomCharacter.toCharacter(): Character {
        return with(this) {
            Character(
                id = id,
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                image = image,
                origin = origin,
                originId = originId,
                location = location,
                locationId = locationId,
                episodes = emptyList()
            )
        }
    }

    fun ApiCharacter.toRoomCharacter(): RoomCharacter {
        return with(this) {
            RoomCharacter(
                id = id,
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                image = image,
                origin = origin.name,
                originId = origin.url.unitId(),
                location = location.name,
                locationId = location.url.unitId(),
            )
        }
    }

}