package com.example.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud.R
import com.example.crud.adapter.PacienteAdapter
import com.example.crud.databinding.PacienteFragmentBinding
import com.example.crud.model.Paciente
import com.example.crud.viewmodel.PacienteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PacienteFragment : Fragment(R.layout.paciente_fragment) {

    companion object {
        fun newInstance() = PacienteFragment()
    }

    private lateinit var viewModel: PacienteViewModel
    private lateinit var binding: PacienteFragmentBinding
    private lateinit var adapterSpinner: ArrayAdapter<String>

    private var selectedPaciente : Paciente? = null

    private val adapter: PacienteAdapter = PacienteAdapter {
        setValueToFields(it)
    }

    private val observerPaciente = Observer<List<Paciente>> {
        adapter.refresh(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PacienteFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(PacienteViewModel::class.java)

        setupRecyclerView()
        setupForm()
        startObservers()
        initialData()

    }


    fun setValueToFields(paciente: Paciente) {
        binding.inputIdTextInputLayout.editText?.setText(paciente.id.toString())
        binding.inputNameTextInputLayout.editText?.setText(paciente.nome)
        binding.inputIdadeTextInputLayout.editText?.setText(paciente.idade.toString())
        binding.inputSexoTextInputLayout.editText?.setText(paciente.sexo)

        binding.inputIdTextInputLayout.visibility = View.VISIBLE
        binding.newButton.visibility = View.GONE

        selectedPaciente = paciente
    }

    fun setupRecyclerView() {
        binding.pacientesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pacientesRecyclerView.adapter = adapter
    }

    fun setupForm() {
        binding.newButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            val idadeStr = binding.inputIdadeTextInputLayout.editText?.text ?: ""
            val sexoStr = binding.inputSexoTextInputLayout.editText?.text ?: ""
//            val priceStr = binding.inputPriceTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty() && idadeStr.isNotEmpty() && sexoStr.isNotEmpty()) {
                Paciente(
                    nome = nameStr.toString(),
                    idade = idadeStr.toString().toInt(),
                    sexo = sexoStr.toString().toInt(),
                ).let {
                    viewModel.insertPaciente(it)
                    clearFields()
                }
            }
        }
//        binding.deleteButton.setOnClickListener {
//            selectedPaciente?.paciente?.let {
//                viewModel.deletePaciente(it)
//                clearFields()
//            }
//        }
        binding.editButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            val idadeStr = binding.inputIdadeTextInputLayout.editText?.text ?: ""
            val sexoStr = binding.inputSexoTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty() && idadeStr.isNotEmpty() && sexoStr.isNotEmpty()) {
                Paciente(
                    nome = nameStr.toString(),
                    idade = idadeStr.toString().toInt(),
                    sexo = sexoStr.toString().toInt()
                ).let {
                    viewModel.updatePaciente(it)
                    clearFields()
                }
            }
        }
    }

    fun clearFields() {
        binding.inputNameTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.editText?.setText("")
        binding.inputIdadeTextInputLayout.editText?.setText("")
        binding.inputSexoTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.visibility = View.GONE
        binding.newButton.visibility = View.VISIBLE

        selectedPaciente = null
    }

    fun startObservers() {
        viewModel.paciente.observe(viewLifecycleOwner, observerPaciente)
    }

    fun initialData() {
        viewModel.getPaciente()
        binding.inputIdTextInputLayout.visibility = View.GONE
    }

}