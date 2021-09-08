package com.example.crud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud.model.Doctor
import com.example.crud.model.DoctorPOJO
import com.example.crud.repository.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository
) : ViewModel() {

    private val _doctor = MutableLiveData<List<DoctorPOJO>>()
    val doctor: LiveData<List<DoctorPOJO>> = _doctor

    private val _singleDoctor = MutableLiveData<DoctorPOJO>()
    val singleDoctor: LiveData<DoctorPOJO> = _singleDoctor

    fun getDoctor() {
        _doctor.value = doctorRepository.getDoctor()
    }

    fun getDoctorById(id: Int) {
        _singleDoctor.value = doctorRepository.getDoctorById(id)
    }

    fun deleteDoctor(doctor: Doctor) {
        doctorRepository.delete(doctor)
        getDoctor()
    }

    fun insertDoctor(doctor: Doctor) {
        doctorRepository.insert(doctor)
        getDoctor()
    }

    fun updateDoctor(doctor: Doctor) {
        doctorRepository.update(doctor)
        getDoctor()
    }

}