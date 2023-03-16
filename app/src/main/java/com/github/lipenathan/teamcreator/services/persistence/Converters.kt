package com.github.lipenathan.teamcreator.services.persistence

import androidx.room.TypeConverter
import com.github.lipenathan.teamcreator.model.Position

class Converters {

    @TypeConverter
    fun toPosition(value: String) = enumValueOf<Position>(value)

    @TypeConverter
    fun fromPosition(value: Position?) = value?.name
}