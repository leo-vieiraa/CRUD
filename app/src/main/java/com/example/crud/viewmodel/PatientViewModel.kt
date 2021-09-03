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

    private val _paciente = MutableLiveData<List<Patient>>()
    val patient: LiveData<List<Patient>> = _paciente

    fun getPaciente() {
        _paciente.value = repository.getPatient()
    }

    fun deletePaciente(patient: Patient) {
        repository.delete(patient)
        getPaciente()
    }

    fun insertPaciente(patient: Patient) {
        repository.insert(patient)
        getPaciente()
    }

    fun updatePaciente(patient: Patient) {
        repository.update(patient)
        getPaciente()
    }

}