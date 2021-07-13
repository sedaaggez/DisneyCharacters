package com.sedaaggez.disneycharacters.model

data class CharacterList(
    val count: Int?,
    val previousPage: String?,
    val nextPage: String?,
    val data: List<Character>?
)