package com.example.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R
import com.example.crud.databinding.DoctorItemFragmentBinding
import com.example.crud.model.DoctorPOJO

class DoctorAdapter (
    val closureClick: (DoctorPOJO) -> Unit
        ) : RecyclerView.Adapter<DoctorViewHolder>() {

    private var listOfDoctors = mutableListOf<DoctorPOJO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_item_fragment, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        listOfDoctors[position].apply {
            holder.bind(this)
        }
        holder.itemView.setOnClickListener {
            closureClick(listOfDoctors[position])
        }
    }

    override fun getItemCount(): Int = listOfDoctors.size

    fun refresh(newList: List<DoctorPOJO>) {
        listOfDoctors = mutableListOf()
        listOfDoctors.addAll(newList)
        notifyDataSetChanged()
    }

}

class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = DoctorItemFragmentBinding.bind(view)

    fun bind(doctor: DoctorPOJO) {

        binding.idTextView.text = doctor.doctor?.id.toString()
        binding.nameTextView.text = doctor.doctor?.name
        binding.specialityTextView.text = doctor.speciality?.name

    }

}