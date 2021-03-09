package com.joaogabriel.marvel.utils

import com.joaogabriel.marvel.model.MarvelData

sealed class Resource<T>(
    val data: MarvelData? = null,
    val message: String? = null) {

    class Success<T>(data: MarvelData) : Resource<T>(data)
    class Error<T>(data: MarvelData? = null, message: String?) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}