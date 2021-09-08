package com.example.crud.database.dao

import androidx.room.*
import com.example.crud.model.Speciality

@Dao
interface SpecialityDAO {

    @Transaction
    @Query("Select * from Speciality order by spe_name")
    fun fetch(): List<Speciality>

    @Transaction
    @Query("Select * from Speciality where spe_id = :id")
    fun fetch(id: Int): Speciality

    @Transaction
    @Query("Select * from Speciality where spe_name like '%' || :name || '%'")
    fun fetch(name: String): List<Speciality>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(list: List<Speciality>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(speciality: Speciality)

    @Delete
    fun delete(speciality: Speciality)

    @Update
    fun update(speciality: Speciality)

}