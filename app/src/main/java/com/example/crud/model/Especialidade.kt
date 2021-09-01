package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Especialidade(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String
)
