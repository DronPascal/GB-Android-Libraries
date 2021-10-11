package com.pascal.rma.data.remote.retrofit.character

import com.pascal.rma.data.remote.retrofit.character.model.ApiCharacter
import com.pascal.rma.domain.model.Character
import com.pascal.rma.util.apiId

/**
 * Created by dronpascal on 12.10.2021.
 */
object ApiCharacterMapper {

    fun ApiCharacter.toCharacter(): Character {
        return with(this) {
            Character(
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
                emptyList()
            )
        }
    }

}