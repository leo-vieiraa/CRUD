package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
)
