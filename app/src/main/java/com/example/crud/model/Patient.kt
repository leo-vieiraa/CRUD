package com.example.crud.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Patient(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val age: Int,
    val gender: Gender
) : Serializable

enum class Gender (val id: Int, val type: String) {
    MALE(0, "Masculino"),
    FEMALE(1, "Feminino"),
    OTHER(2, "Outro")
}
