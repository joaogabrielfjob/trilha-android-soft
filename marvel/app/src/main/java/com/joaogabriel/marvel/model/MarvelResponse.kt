package com.joaogabriel.marvel.model

import kotlin.collections.ArrayList

data class MarvelData(val data: MarvelResults)

data class MarvelResults(val results: ArrayList<MarvelResult>)

data class MarvelResult(val name: String, val comics: MarvelComics)

data class MarvelComics(val items: ArrayList<MarvelComic>)

data class MarvelComic(val name: String, resourceURI: )