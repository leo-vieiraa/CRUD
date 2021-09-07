package com.example.crud.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Patient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pat_id")
    var id: Int = 0,
    @ColumnInfo(name = "pat_name")
    val name: String,
    @ColumnInfo(name = "pat_age")
    val age: Int,
    @ColumnInfo(name = "pat_gender")
    val gender: Gender
) : Serializable {

    override fun toString(): String {
        return "$name - $id"
    }

}

enum class Gender (val id: Int, val type: String) {
    MALE(0, "Masculino"),
    FEMALE(1, "Feminino"),
    OTHER(2, "Outro")
}
