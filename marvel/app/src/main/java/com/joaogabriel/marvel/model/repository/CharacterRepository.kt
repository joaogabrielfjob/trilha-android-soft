package com.joaogabriel.marvel.model.repository

import com.joaogabriel.marvel.model.MarvelData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterRepository {

    @GET("characters")
    fun getCharacters(@Query("apikey") apikey: String,
                      @Query("ts") ts: String,
                      @Query("hash") hash: String): Call<MarvelData>
}