package com.example.crud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud.model.Patient
import com.example.crud.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(
    private val repository: PatientRepository
) : ViewModel() {

    private val _patient = MutableLiveData<List<Patient>>()
    val patient: LiveData<List<Patient>> = _patient

    private val _singlePatient = MutableLiveData<Patient>()
    val singlePatient: LiveData<Patient> = _singlePatient

    fun getPatient() {
        _patient.value = repository.getPatient()
    }

    fun getPatientById(id: Int) {
        _singlePatient.value = repository.getPatientById(id)
    }

    fun deletePatient(patient: Patient) {
        repository.delete(patient)
        getPatient()
    }

    fun insertPatient(patient: Patient) {
        repository.insert(patient)
        getPatient()
    }

    fun updatePatient(patient: Patient) {
        repository.update(patient)
        getPatient()
    }

}