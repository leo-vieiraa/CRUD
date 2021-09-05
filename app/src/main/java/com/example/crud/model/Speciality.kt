package com.example.crud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.crud.database.Converter

@Entity
data class Speciality(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "spe_id")
    var id: Int = 0,
    @ColumnInfo(name = "spe_name")
    val name: String
)