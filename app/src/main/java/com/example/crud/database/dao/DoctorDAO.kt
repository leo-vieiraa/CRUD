package com.example.crud.database.dao

import androidx.room.*
import com.example.crud.model.Doctor
import com.example.crud.model.DoctorPOJO

@Dao
interface DoctorDAO {

    @Transaction
    @Query("Select * from Doctor order by doc_name")
    fun fetch(): List<DoctorPOJO>

    @Transaction
    @Query("Select * from Doctor where doc_id = :id")
    fun fetch(id: Int): DoctorPOJO

    @Transaction
    @Query("Select * from Doctor where doc_name like '%' || :name || '%'")
    fun fetch(name: String): List<DoctorPOJO>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(list: List<Doctor>)

    @Delete
    fun delete(speciality: Doctor)

    @Update
    fun update(speciality: Doctor)

}