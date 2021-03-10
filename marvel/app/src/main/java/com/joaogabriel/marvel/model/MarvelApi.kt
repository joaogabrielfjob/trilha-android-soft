package com.joaogabriel.marvel.model

import kotlin.collections.ArrayList

data class MarvelData(val data: MarvelResults)

data class MarvelResults(val results: ArrayList<Character>)

data class Character(val name: String, val description: String, val comics: Comics, val thumbnail: CharacterImage)

data class Comics(val items: ArrayList<Comic>)

data class Comic(val name: String)

data class CharacterImage(val path: String, val extension: String) {
    fun getPathExtension(): String {
        return "$path.$extension"
    }
}