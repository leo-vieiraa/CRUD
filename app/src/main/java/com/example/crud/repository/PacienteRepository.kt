package com.example.crud.repository

import com.example.crud.database.AppDatabase
import com.example.crud.database.dao.PacienteDAO
import com.example.crud.model.Paciente
import javax.inject.Inject

class PacienteRepository @Inject constructor(
    val pacienteRepository: PacienteDAO
){

    fun getPaciente(): List<Paciente> {
        return pacienteRepository.getAll()
    }

    fun insert(paciente: Paciente) {
        return pacienteRepository.insert(paciente)
    }

    fun delete(paciente: Paciente) {
        return pacienteRepository.delete(paciente)
    }

    fun update(paciente: Paciente) {
        return pacienteRepository.update(paciente)
    }

}