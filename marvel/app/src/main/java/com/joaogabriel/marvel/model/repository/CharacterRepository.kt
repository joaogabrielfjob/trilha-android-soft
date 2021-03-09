package com.joaogabriel.marvel.model.repository

import com.joaogabriel.marvel.utils.MarvelApiRequests
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterRepository {
    private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"

    fun makeRequest(): MarvelApiRequests {
        return  Retrofit.
        Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(MarvelApiRequests::class.java)
    }
}