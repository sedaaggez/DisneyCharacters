package com.sedaaggez.disneycharacters.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class CharacterList(
    @ColumnInfo(name = "count")
    val count: Integer?,
    @ColumnInfo(name = "previousPage")
    val previousPage: String?,
    @ColumnInfo(name = "nextPage")
    val nextPage: String?,
    @ColumnInfo(name = "data")
    val data: List<Character>?
)