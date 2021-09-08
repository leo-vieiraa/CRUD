package com.example.crud.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.crud.model.Patient

@Dao
interface PatientDAO {

    @Transaction
    @Query("SELECT * FROM Patient")
    fun fetch(): List<Patient>

    @Transaction
    @Query("SELECT * FROM Patient WHERE pat_id = :id")
    fun fetch(id: Int): Patient

    @Query("Select * from Patient where pat_gender = :gender")
    fun fetch(gender: String): List<Patient>

    @Insert(onConflict = ABORT)
    fun insert(patient: Patient)

    @Insert(onConflict = ABORT)
    fun insert(list: List<Patient>)

    @Delete
    fun delete(patient: Patient)

    @Update
    fun update(patient: Patient)

}