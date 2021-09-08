package com.example.crud.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud.R
import com.example.crud.adapter.PatientAdapter
import com.example.crud.databinding.PatientFragmentBinding
import com.example.crud.model.Gender
import com.example.crud.model.Patient
import com.example.crud.model.Speciality
import com.example.crud.viewmodel.PatientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientFragment : Fragment(R.layout.patient_fragment) {

    companion object {
        fun newInstance() = PatientFragment()
    }

    private lateinit var viewModel: PatientViewModel
    private lateinit var binding: PatientFragmentBinding
    private lateinit var selectedPatient: Patient

    private var selectedGender: Gender? = null
    private val adapter: PatientAdapter = PatientAdapter {
        setValueToFields(it)

    }

    private val observerPatient = Observer<List<Patient>> {
        adapter.refresh(it)
    }
    private val observerSinglePatient = Observer<Patient> { patient ->
        selectedPatient = patient
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PatientFragmentBinding.bind(view)
        binding.patientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.patientsRecyclerView.adapter = adapter
        binding.inputIdTextInputLayout.visibility = View.GONE


        viewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        viewModel.patient.observe(viewLifecycleOwner, observerPatient)
        viewModel.singlePatient.observe(viewLifecycleOwner, observerSinglePatient)
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
        binding.deleteButton.setOnClickListener {

            var idStr = binding.inputIdTextInputLayout.editText?.text.toString()

            viewModel.getPatientById(idStr.toInt()).let {

                viewModel.deletePatient(selectedPatient)

            }

            clearFields()

        }
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

        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().windowToken, 0)

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