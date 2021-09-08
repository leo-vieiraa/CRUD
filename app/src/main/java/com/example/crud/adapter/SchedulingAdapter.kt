package com.example.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R
import com.example.crud.databinding.SchedulingItemFragmentBinding
import com.example.crud.model.SchedulingPOJO
import com.example.crud.model.Speciality

class SchedulingAdapter : RecyclerView.Adapter<SchedulingViewHolder>(){

    private var listOfScheduling = mutableListOf<SchedulingPOJO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scheduling_item_fragment, parent, false)
        return SchedulingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchedulingViewHolder, position: Int) {
        listOfScheduling[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOfScheduling.size

    fun refresh(schedulingPOJO: List<SchedulingPOJO>) {
        listOfScheduling = mutableListOf()
        listOfScheduling.addAll(schedulingPOJO)
        notifyDataSetChanged()
    }
}

class SchedulingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = SchedulingItemFragmentBinding.bind(view)

    fun bind(schedulingPOJO: SchedulingPOJO) {

        binding.idTextView.text = schedulingPOJO.scheduling.id.toString()
        binding.ageTextView.text = schedulingPOJO.patient.age.toString()
        binding.labelPatientNameTextView.text = "Patient Name:"
        binding.patientNameTextView.text = schedulingPOJO.patient.name
        binding.genderTextView.text = schedulingPOJO.patient.gender.type
        binding.labelDoctorNameTextView.text = "Doctor Name:"
        binding.doctorNameTextView.text = schedulingPOJO.doctor.name
        binding.specialityTextView.text = schedulingPOJO.doctor.speciality.toString()

    }

}