package com.example.crud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud.model.Paciente
import com.example.crud.repository.PacienteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PacienteViewModel @Inject constructor(
    private val repository: PacienteRepository
) : ViewModel() {

    private val _paciente = MutableLiveData<List<Paciente>>()
    val paciente: LiveData<List<Paciente>> = _paciente

    fun getPaciente() {
        _paciente.value = repository.getPaciente()
    }

    fun deletePaciente(paciente: Paciente) {
        repository.delete(paciente)
        getPaciente()
    }

    fun insertPaciente(paciente: Paciente) {
        repository.insert(paciente)
        getPaciente()
    }

    fun updatePaciente(paciente: Paciente) {
        repository.update(paciente)
        getPaciente()
    }

}