package com.joaogabriel.marvel.model

data class Character(
    val name: String,
    val description: String,
    val comics: List<Comic>
)
