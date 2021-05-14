package com.sedaaggez.disneycharacters.model

data class Character(
    val id: Integer?,
    val url: String?,
    val name: String?,
    val sourceUrl: String?,
    val imageUrl: String?,
    val films: List<String>?,
    val shortFilms: List<String>?,
    val tvShows: List<String>?,
    val videoGames: List<String>?,
    val alignment: String?,
    val parkAttractions: List<String>?,
    val allies: List<String>?,
    val enemies: List<String>?
)