package com.sedaaggez.disneycharacters.model

data class CharacterList(
    val count: Integer?,
    val previousPage: String?,
    val nextPage: String?,
    val data: List<Character>?
)
