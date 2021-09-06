package com.example.crud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud.model.Speciality
import com.example.crud.repository.SpecialityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialityViewModel @Inject constructor(
    private val specialityRepository: SpecialityRepository
) : ViewModel() {

    private val _speciality = MutableLiveData<List<Speciality>>()
    val speciality: LiveData<List<Speciality>> = _speciality

    fun getSpeciality() {
        _speciality.value = specialityRepository.fetch()
    }

    fun addSpeciality(speciality: Speciality) {
        specialityRepository.insert(speciality)
        getSpeciality()
    }

}