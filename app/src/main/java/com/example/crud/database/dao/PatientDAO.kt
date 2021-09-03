package com.example.crud.database.dao

import androidx.room.*
import com.example.crud.model.Patient

@Dao
interface PatientDAO {

    @Transaction
    @Query("SELECT * FROM Patient")
    fun getAll(): List<Patient>

    @Transaction
    @Query("SELECT * FROM Patient WHERE id = :id")
    fun getById(id: Long): Patient

    @Insert
    fun insert(patient: Patient)

    @Delete
    fun delete(patient: Patient)

    @Update
    fun update(patient: Patient)

}