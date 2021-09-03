package com.example.crud.repository

import com.example.crud.database.dao.PatientDAO
import com.example.crud.model.Patient
import javax.inject.Inject

class PatientRepository @Inject constructor(
    val patientRepository: PatientDAO
){

    fun getPatient(): List<Patient> {
        return patientRepository.getAll()
    }

    fun insert(patient: Patient) {
        return patientRepository.insert(patient)
    }

    fun delete(patient: Patient) {
        return patientRepository.delete(patient)
    }

    fun update(patient: Patient) {
        return patientRepository.update(patient)
    }

}