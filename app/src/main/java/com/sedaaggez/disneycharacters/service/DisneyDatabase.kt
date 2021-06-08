package com.sedaaggez.disneycharacters.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sedaaggez.disneycharacters.util.Converters

@Database(entities = arrayOf(com.sedaaggez.disneycharacters.model.Character::class), version = 1)
@TypeConverters(Converters::class)
abstract class DisneyDatabase : RoomDatabase() {
    abstract fun disneyDao() : DisneyDao

    companion object {
        @Volatile private var instance : DisneyDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, DisneyDatabase::class.java, "disneydatabase"
        ).build()
    }
}