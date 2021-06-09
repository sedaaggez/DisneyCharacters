package com.sedaaggez.disneycharacters.util

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(stringList: List<String>?): String {
        if (stringList == null) return ""
        return stringList.joinToString(",")
    }

    @TypeConverter
    fun fromStringToStringList(list: String?): List<String> {
        if (list == null) return ArrayList()
        return list.split(",").map { it }
    }
}