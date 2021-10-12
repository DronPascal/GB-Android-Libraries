package com.pascal.rma.data.remote.retrofit.character

import com.pascal.rma.data.remote.retrofit.character.model.ApiCharacter
import com.pascal.rma.data.remote.retrofit.character.model.ApiCharactersPage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by dronpascal on 08.10.2021.
 */
interface CharacterApiService {

    @GET("character/")
    fun getCharactersPage(
        @Query("page") page: Int,
    ): Single<ApiCharactersPage>

    @GET("character/{id}")
    fun getCharacterById(
        @Path("id") id: Int
    ): Single<ApiCharacter>

    @GET
    fun getCharacterByUrl(@Url url: String): Single<ApiCharacter>

}