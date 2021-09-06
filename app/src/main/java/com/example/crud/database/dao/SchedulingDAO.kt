package com.example.crud.database.dao

import androidx.room.*
import com.example.crud.model.Scheduling
import com.example.crud.model.SchedulingPOJO

@Dao
interface SchedulingDAO {

    @Transaction
    @Query("Select * from Scheduling s, Doctor d where s.doctorFK = d.doc_id order by d.doc_name")
    fun fetch(): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling where sch_id = :id")
    fun fetch(id: Int): SchedulingPOJO

    @Transaction
    @Query("Select * from Scheduling inner join Doctor on doctor.doc_id = doctorFK where doctor.doc_name like '%' || :name || '%'")
    fun fetchByPhysician(name: String): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling inner join Doctor on doctor.doc_id = doctorFK where doctor.speciality in (:ids)")
    fun fetchByPhysicianSpeciality(ids: List<Int>): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling inner join Patient on patient.pat_id = patientFK where patient.pat_name like '%' || :name || '%'")
    fun fetchByPatient(name: String): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling inner join Patient on patient.pat_id = patientFK where pat_gender = :gender")
    fun fetchByGender(gender: String): List<SchedulingPOJO>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(list: List<Scheduling>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(list: Scheduling)

    @Delete
    fun delete(scheduling: Scheduling)

    @Update
    fun update(scheduling: Scheduling)

}