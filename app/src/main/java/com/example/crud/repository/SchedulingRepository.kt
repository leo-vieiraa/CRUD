package com.example.crud.repository

import com.example.crud.database.dao.SchedulingDAO
import com.example.crud.model.Scheduling
import com.example.crud.model.SchedulingPOJO
import javax.inject.Inject

class SchedulingRepository @Inject constructor(
    val schedulingRepository: SchedulingDAO
){

    fun getSchedule(): List<SchedulingPOJO> {
        return schedulingRepository.fetch()
    }

    fun insert(scheduling: Scheduling) {
        return schedulingRepository.insert(scheduling)
    }

    fun delete(scheduling: Scheduling) {
        return schedulingRepository.delete(scheduling)
    }

    fun update(scheduling: Scheduling) {
        return schedulingRepository.update(scheduling)
    }

}