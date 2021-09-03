package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val name: String,
    val age: Int,
    val gender: Int
)

enum class Gender{
    MASCULINO,
    FEMININO,
    OUTROS
}
