package com.sedaaggez.disneycharacters.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sedaaggez.disneycharacters.model.Character

@Dao
interface DisneyDao {
    @Insert
    suspend fun insertAll(vararg characters: Character): List<Long>

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<Character>

    @Query("SELECT * FROM character WHERE id = :characterId")
    suspend fun getCharacter(characterId: Int): Character

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()
}