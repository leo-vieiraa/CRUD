package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.crud.database.Converter

@Entity
data class Speciality(
    @TypeConverters(Converter::class)
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String
)
