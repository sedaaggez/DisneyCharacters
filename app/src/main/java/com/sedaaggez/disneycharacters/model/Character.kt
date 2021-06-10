package com.sedaaggez.disneycharacters.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @ColumnInfo(name = "id")
    val id: Integer?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "source_url")
    val sourceUrl: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "films")
    val films: List<String>?,
    @ColumnInfo(name = "short_films")
    val shortFilms: List<String>?,
    @ColumnInfo(name = "tv_shows")
    val tvShows: List<String>?,
    @ColumnInfo(name = "video_games")
    val videoGames: List<String>?,
    @ColumnInfo(name = "alignment")
    val alignment: String?,
    @ColumnInfo(name = "park_attractions")
    val parkAttractions: List<String>?,
    @ColumnInfo(name = "allies")
    val allies: List<String>?,
    @ColumnInfo(name = "enemies")
    val enemies: List<String>?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}