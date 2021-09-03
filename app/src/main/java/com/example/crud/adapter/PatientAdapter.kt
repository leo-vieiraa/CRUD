package com.example.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R
import com.example.crud.databinding.PatientItemFragmentBinding
import com.example.crud.model.Patient

class PatientAdapter(
    private val onTap: (Patient) -> Unit
) : RecyclerView.Adapter<PatientViewHolder>() {

    private var listOf = mutableListOf<Patient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.patient_item_fragment, parent, false).let {
            PatientViewHolder(it, onTap)
        }

    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        listOf[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOf.size

    fun refresh(newList: List<Patient>) {
        listOf = mutableListOf()
        listOf.addAll(newList)
        notifyDataSetChanged()
    }

}

class PatientViewHolder(view: View, val onTap: (Patient) -> Unit ): RecyclerView.ViewHolder(view) {

    private val binding: PatientItemFragmentBinding = PatientItemFragmentBinding.bind(view)

    fun bind(patient: Patient) {
        binding.idTextView.text = patient.id.toString()
        binding.nameTextView.text = patient.name
        binding.genderTextView.text = patient.gender.toString()
        binding.ageTextView.text = patient.age.toString()
    }

}