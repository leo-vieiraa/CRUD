package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medico(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val especialidade: Especialidade
)
