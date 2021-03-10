package com.joaogabriel.marvel.model

import com.joaogabriel.marvel.model.repository.CharacterRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequest {
    private val BASE_URL = "https://gateway.marvel.com:443/v1/public/"

    fun makeRequest(): CharacterRepository {
        return  Retrofit.
        Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build().
        create(CharacterRepository::class.java)
    }
}