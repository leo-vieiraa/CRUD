package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medico(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val nome: String,
)
