package com.pascal.rma.data.remote.retrofit.character

import com.pascal.rma.data.remote.retrofit.character.model.CharacterPageDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by dronpascal on 08.10.2021.
 */
interface CharacterApiService {

    @GET("/character")
    fun getCharacters(): Single<CharacterPageDto>

    fun getCharacterPage(@Url pageUrl: String): Single<CharacterPageDto>
}