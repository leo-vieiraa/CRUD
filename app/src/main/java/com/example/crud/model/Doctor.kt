package com.example.crud.model

import androidx.room.*
import java.io.Serializable

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "doc_id")
    var id: Int = 0,
    @ColumnInfo(name = "doc_name")
    val name: String,
    val speciality: Int
) : Serializable

data class DoctorPOJO(
    @Embedded
    val doctor: Doctor?,
    @Relation(
        parentColumn = "speciality",
        entityColumn = "spe_id"
    )
    val speciality: Speciality?
) : Serializable
