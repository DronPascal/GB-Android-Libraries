package com.pascal.rma.data.remote

import com.pascal.rma.data.remote.retrofit.character.ApiCharacterMapper.toCharacter
import com.pascal.rma.data.remote.retrofit.character.CharacterApiService
import com.pascal.rma.domain.model.Character
import io.reactivex.rxjava3.core.Single

/**
 * Created by dronpascal on 05.10.2021.
 */
class CharacterService(
    private val apiService: CharacterApiService
) : ICharacterService {

    override fun getCharacter(id: Int): Single<Character> {
        return apiService.getCharacterById(id)
            .map { it.toCharacter() }
    }
}