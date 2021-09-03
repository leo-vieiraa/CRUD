package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Paciente(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val nome: String,
    val idade: Int,
    val sexo: Int
)

enum class Sexo{
    MASCULINO,
    FEMININO,
    OUTROS
}
