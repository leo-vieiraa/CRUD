package com.example.crud.database.dao

import androidx.room.*
import com.example.crud.model.Paciente

@Dao
interface PacienteDAO {

    @Transaction
    @Query("SELECT * FROM Paciente")
    fun getAll(): List<Paciente>

    @Transaction
    @Query("SELECT * FROM Paciente WHERE id = :id")
    fun getById(id: Long): Paciente

    @Insert
    fun insert(paciente: Paciente)

    @Delete
    fun delete(paciente: Paciente)

    @Update
    fun update(paciente: Paciente)

}