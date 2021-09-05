package com.example.crud.model

import androidx.room.*

@Entity
data class Scheduling(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sch_id")
    var id: Int = 0,
    val patientFK: Int,
    val doctorFK: Int
)

data class SchedulingPOJO(
    @Embedded
    val scheduling: Scheduling,

    @Relation(
        parentColumn = "patientFK",
        entityColumn = "pat_id"
    )
    val patient: Patient,

    @Relation(
        parentColumn = "doctorFK",
        entityColumn = "doc_id"
    )
    val doctor: DoctorPOJO
)

