package com.example.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud.R
import com.example.crud.adapter.PatientAdapter
import com.example.crud.databinding.PatientFragmentBinding
import com.example.crud.model.Gender
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

    private var selectedGender: Gender? = null

    private val adapter: PatientAdapter = PatientAdapter {
        setValueToFields(it)

        val bundle = Bundle()
        bundle.putSerializable("patient", it)

        val fragment = PatientDetailsFragment.newInstance()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager
            .beginTransaction()
            .hide(this)
            .add(R.id.container, fragment)
            .addToBackStack("patient")
            .commit()

    }

    private val observerPaciente = Observer<List<Patient>> {
        adapter.refresh(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PatientFragmentBinding.bind(view)
        binding.pacientesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.pacientesRecyclerView.adapter = adapter
        binding.inputIdTextInputLayout.visibility = View.GONE


        viewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        viewModel.patient.observe(viewLifecycleOwner, observerPaciente)
        viewModel.getPatient()

        setupForm()
        setupSpinnerGender()

    }

    fun setupForm() {
        binding.newButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            val ageStr = binding.inputIdadeTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty() && ageStr.isNotEmpty() && selectedGender != null) {
                Patient(
                    name = nameStr.toString(),
                    age = ageStr.toString().toInt(),
                    gender = selectedGender!!,
                ).let {
                    viewModel.insertPatient(it)
                    clearFields()
                }
            }
        }
//        binding.deleteButton.setOnClickListener {
//            val idStr = binding.inputIdTextInputLayout.editText?.text ?: ""
//            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
//            val ageStr = binding.inputIdadeTextInputLayout.editText?.text ?: ""
//
//            Patient(
//                id = idStr.toInt(),
//                name = nameStr.toString(),
//                age = ageStr.toString().toInt(),
//                gender = selectedGender!!
//            ).let {
//                viewModel.deletePatient(it)
//                clearFields()
//            }
//        }
        binding.editButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            val ageStr = binding.inputIdadeTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty() && ageStr.isNotEmpty() && selectedGender != null) {
                Patient(
                    name = nameStr.toString(),
                    age = ageStr.toString().toInt(),
                    gender = selectedGender!!
                ).let {
                    viewModel.updatePatient(it)
                    clearFields()
                }
            }
        }
    }

    private fun setupSpinnerGender() {
        val listOfGender = Gender.values().map { gender ->
            gender.type
        }

        val adapterSpinner =
            ArrayAdapter(
                requireContext(),
                R.layout.spinner_item_gender,
                listOfGender
            )

        val autoCompleteBrand: AutoCompleteTextView? =
            binding.inputGenderTextInputLayout.editText as? AutoCompleteTextView

        autoCompleteBrand?.threshold = 1

        autoCompleteBrand?.setAdapter(adapterSpinner)

        autoCompleteBrand?.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as String
            selectedGender = Gender.values().find {
                it.type == selected
            }
        }
    }


    fun clearFields() {
        binding.inputNameTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.editText?.setText("")
        binding.inputIdadeTextInputLayout.editText?.setText("")
        binding.inputGenderTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.visibility = View.GONE
        binding.newButton.visibility = View.VISIBLE

        selectedGender = null
    }

    fun setValueToFields(patient: Patient) {
        binding.inputIdTextInputLayout.editText?.setText(patient.id.toString())
        binding.inputNameTextInputLayout.editText?.setText(patient.name)
        binding.inputIdadeTextInputLayout.editText?.setText(patient.age.toString())
        binding.inputGenderTextInputLayout.editText?.setText(patient.gender.type)

        binding.inputIdTextInputLayout.visibility = View.VISIBLE
        binding.newButton.visibility = View.GONE

//        selectedGender = patient
    }

}