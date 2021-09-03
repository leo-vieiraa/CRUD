package com.example.crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R
import com.example.crud.databinding.PacienteItemFragmentBinding
import com.example.crud.model.Paciente

class PacienteAdapter(
    private val onTap: (Paciente) -> Unit
) : RecyclerView.Adapter<PacienteViewHolder>() {

    private var listOf = mutableListOf<Paciente>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.paciente_item_fragment, parent, false).let {
            PacienteViewHolder(it, onTap)
        }

    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        listOf[position].apply {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int = listOf.size

    fun refresh(newList: List<Paciente>) {
        listOf = mutableListOf()
        listOf.addAll(newList)
        notifyDataSetChanged()
    }

}

class PacienteViewHolder(view: View, val onTap: (Paciente) -> Unit ): RecyclerView.ViewHolder(view) {

    private val binding: PacienteItemFragmentBinding = PacienteItemFragmentBinding.bind(view)

    fun bind(paciente: Paciente) {
        binding.idTextView.text = paciente.id.toString()
        binding.nameTextView.text = paciente.nome
        binding.sexoTextView.text = paciente.sexo.toString()
        binding.idadeTextView.text = paciente.idade.toString()
    }

}