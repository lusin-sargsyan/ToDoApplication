package com.example.todoapplication.data

import androidx.room.TypeConverter
import com.example.todoapplication.domain.entities.ToDo
import com.example.todoapplication.extension.convertJsonToString
import com.example.todoapplication.extension.convertStringToJson

class Converters {
    @TypeConverter
    fun fromList(list: List<ToDo>): String {
        return list.convertJsonToString()
    }

    @TypeConverter
    fun toList(string: String): List<ToDo> {
        return string.convertStringToJson()
    }
}