package com.pascal.rma.data.cache.room.character

import com.pascal.rma.data.remote.retrofit.character.model.ApiCharacter
import com.pascal.rma.domain.model.Character
import com.pascal.rma.util.apiId

/**
 * Created by dronpascal on 12.10.2021.
 */
object RoomCharacterMappers {

    fun Character.toRoomCharacter(): RoomCharacter {
        return with(this) {
            RoomCharacter(
                id,
                name,
                status,
                species,
                type,
                gender,
                image,
                origin,
                originId,
                location,
                locationId
            )
        }
    }

    fun RoomCharacter.toCharacter(): Character {
        return with(this) {
            Character(
                id,
                name,
                status,
                species,
                type,
                gender,
                image,
                origin,
                originId,
                location,
                locationId,
                emptyList()
            )
        }
    }

    fun ApiCharacter.toRoomCharacter(): RoomCharacter {
        return with(this) {
            RoomCharacter(
                id,
                name,
                status,
                species,
                type,
                gender,
                image,
                origin.name,
                origin.url.apiId(),
                location.name,
                location.url.apiId(),
            )
        }
    }

}