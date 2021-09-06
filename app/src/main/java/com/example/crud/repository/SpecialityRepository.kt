package com.example.crud.repository

import com.example.crud.database.dao.SpecialityDAO
import com.example.crud.model.Speciality
import javax.inject.Inject

class SpecialityRepository @Inject constructor(
    private val specialityDAO: SpecialityDAO
) {

    fun fetch(): List<Speciality> {
        return specialityDAO.fetch()
    }

    fun fetch(id: Int): Speciality {
        return specialityDAO.fetch(id)
    }

    fun insert(speciality: Speciality) {
        specialityDAO.insert(speciality)
    }

    fun update(speciality: Speciality) {
        specialityDAO.update(speciality)
    }

    fun delete(speciality: Speciality) {
        specialityDAO.delete(speciality)
    }

}