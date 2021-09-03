package com.example.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud.R
import com.example.crud.adapter.PatientAdapter
import com.example.crud.databinding.PatientFragmentBinding
import com.example.crud.model.Patient
import com.example.crud.viewmodel.PatientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientFragment : Fragment(R.layout.patient_fragment) {

    companion object {
        fun newInstance() = PatientFragment()
    }

    private lateinit var viewModel: PatientViewModel
    private lateinit var binding: PatientFragmentBinding
    private lateinit var adapterSpinner: ArrayAdapter<String>

    private var selectedPatient : Patient? = null

    private val adapter: PatientAdapter = PatientAdapter {
        setValueToFields(it)
    }

    private val observerPaciente = Observer<List<Patient>> {
        adapter.refresh(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PatientFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(PatientViewModel::class.java)

        setupRecyclerView()
        setupForm()
        startObservers()
        initialData()

    }


    fun setValueToFields(patient: Patient) {
        binding.inputIdTextInputLayout.editText?.setText(patient.id.toString())
        binding.inputNameTextInputLayout.editText?.setText(patient.name)
        binding.inputIdadeTextInputLayout.editText?.setText(patient.age.toString())
        binding.inputSexoTextInputLayout.editText?.setText(patient.gender)

        binding.inputIdTextInputLayout.visibility = View.VISIBLE
        binding.newButton.visibility = View.GONE

        selectedPatient = patient
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
                Patient(
                    name = nameStr.toString(),
                    age = idadeStr.toString().toInt(),
                    gender = sexoStr.toString().toInt(),
                ).let {
                    viewModel.insertPaciente(it)
                    clearFields()
                }
            }
        }
//        binding.deleteButton.setOnClickListener {
//            selectedPatient?.patient?.let {
//                viewModel.deletePaciente(it)
//                clearFields()
//            }
//        }
        binding.editButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            val idadeStr = binding.inputIdadeTextInputLayout.editText?.text ?: ""
            val sexoStr = binding.inputSexoTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty() && idadeStr.isNotEmpty() && sexoStr.isNotEmpty()) {
                Patient(
                    name = nameStr.toString(),
                    age = idadeStr.toString().toInt(),
                    gender = sexoStr.toString().toInt()
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

        selectedPatient = null
    }

    fun startObservers() {
        viewModel.patient.observe(viewLifecycleOwner, observerPaciente)
    }

    fun initialData() {
        viewModel.getPaciente()
        binding.inputIdTextInputLayout.visibility = View.GONE
    }

}