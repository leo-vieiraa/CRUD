package com.example.crud.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud.model.Scheduling
import com.example.crud.model.SchedulingPOJO
import com.example.crud.repository.SchedulingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SchedulingViewModel @Inject constructor(
    val schedulingRepository: SchedulingRepository
) : ViewModel() {

    private val _scheduling = MutableLiveData<List<SchedulingPOJO>>()
    val scheduling: LiveData<List<SchedulingPOJO>> = _scheduling

    fun getSchedule() {
        _scheduling.value = schedulingRepository.getSchedule()
    }

    fun delete(scheduling: Scheduling) {
        schedulingRepository.delete(scheduling)
        getSchedule()
    }

    fun insert(scheduling: Scheduling) {
        schedulingRepository.insert(scheduling)
        getSchedule()
    }

    fun update(scheduling: Scheduling) {
        schedulingRepository.update(scheduling)
        getSchedule()
    }

}