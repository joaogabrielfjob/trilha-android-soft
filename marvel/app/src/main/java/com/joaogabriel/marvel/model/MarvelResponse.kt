package com.joaogabriel.marvel.model

import kotlin.collections.ArrayList

data class MarvelData(val data: MarvelResults)

data class MarvelResults(val results: ArrayList<MarvelResult>)

data class MarvelResult(val name: String, val description: String, val comics: MarvelComics, val thumbnail: CharacterImage)

data class MarvelComics(val items: ArrayList<MarvelComic>)

data class MarvelComic(val name: String)

data class CharacterImage(val path: String, val extension: String) {
    fun getPathExtension(): String {
        return "$path.$extension"
    }
}