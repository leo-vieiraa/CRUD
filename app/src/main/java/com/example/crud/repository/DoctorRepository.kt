package com.example.crud.repository

import com.example.crud.database.dao.DoctorDAO
import com.example.crud.model.Doctor
import com.example.crud.model.DoctorPOJO
import javax.inject.Inject

class DoctorRepository @Inject constructor(
    private val doctorRepository: DoctorDAO
){

    fun getDoctor(): List<DoctorPOJO> {
        return doctorRepository.fetch()
    }

    fun insert(doctor: Doctor) {
        return doctorRepository.insert(doctor)
    }

    fun delete(doctor: Doctor) {
        return doctorRepository.delete(doctor)
    }

    fun update(doctor: Doctor) {
        return doctorRepository.update(doctor)
    }

}