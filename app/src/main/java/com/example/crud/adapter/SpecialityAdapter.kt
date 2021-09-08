package com.example.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R
import com.example.crud.databinding.SpecialityItemFragmentBinding
import com.example.crud.model.Speciality

class SpecialityAdapter (
    private val closureClick: (Speciality) -> Unit
        ) : RecyclerView.Adapter<SpecialityViewHolder>() {

    private var listOfSpecialities = mutableListOf<Speciality>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.speciality_item_fragment, parent, false)
        return SpecialityViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialityViewHolder, position: Int) {
        listOfSpecialities[position].apply {
            holder.bind(this)
        }
        holder.itemView.setOnClickListener {
            closureClick(listOfSpecialities[position])
        }
    }

    override fun getItemCount(): Int = listOfSpecialities.size

    fun refresh(newList: List<Speciality>) {
        listOfSpecialities = mutableListOf()
        listOfSpecialities.addAll(newList)
        notifyDataSetChanged()
    }

}

class SpecialityViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val binding = SpecialityItemFragmentBinding.bind(view)

    fun bind(speciality: Speciality) {
        binding.idTextView.text = speciality.id.toString()
        binding.nameTextView.text = speciality.name
    }

}