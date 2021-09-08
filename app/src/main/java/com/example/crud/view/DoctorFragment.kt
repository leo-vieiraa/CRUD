package com.example.crud.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.view.inspector.StaticInspectionCompanionProvider
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud.R
import com.example.crud.adapter.DoctorAdapter
import com.example.crud.databinding.DoctorFragmentBinding
import com.example.crud.databinding.PatientFragmentBinding
import com.example.crud.model.*
import com.example.crud.viewmodel.DoctorViewModel
import com.example.crud.viewmodel.PatientViewModel
import com.example.crud.viewmodel.SpecialityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorFragment : Fragment(R.layout.doctor_fragment) {

    companion object {
        fun newInstance() = DoctorFragment()
    }

    private lateinit var viewModel: DoctorViewModel
    private lateinit var specialityViewModel: SpecialityViewModel
    private lateinit var binding: DoctorFragmentBinding
    private lateinit var selectedDoctor: DoctorPOJO
    private var selectedSpeciality: Speciality? = null
    private val adapter = DoctorAdapter() { doctor ->

        setValueToFields(doctor)

    }

    private val observerDoctor = Observer<List<DoctorPOJO>> {
        adapter.refresh(it)
    }

    private val observerSingleDoctor = Observer<DoctorPOJO> { doctor ->
        selectedDoctor = doctor
    }

    private val observerSpecialities = Observer<List<Speciality>> { specialities ->
        setupSpinnerSpeciality(specialities)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DoctorFragmentBinding.bind(view)
        binding.doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.doctorsRecyclerView.adapter = adapter
        binding.inputIdTextInputLayout.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        viewModel.doctor.observe(viewLifecycleOwner, observerDoctor)
        viewModel.singleDoctor.observe(viewLifecycleOwner, observerSingleDoctor)

        specialityViewModel = ViewModelProvider(this).get(SpecialityViewModel::class.java)
        specialityViewModel.speciality.observe(viewLifecycleOwner, observerSpecialities)

        setupForm()

        viewModel.getDoctor()
        specialityViewModel.getSpeciality()

    }

    fun setupForm() {
        binding.newButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            if (nameStr.isNotEmpty() && selectedSpeciality != null) {
                Doctor(
                    name = nameStr.toString(),
                    speciality = selectedSpeciality!!.id,
                ).let {
                    viewModel.insertDoctor(it)
//                    clearFields()
                }
            }

            clearFields()

        }
        binding.deleteButton.setOnClickListener {

            var idStr = binding.inputIdTextInputLayout.editText?.text.toString()

            viewModel.getDoctorById(idStr.toInt()).let {

                viewModel.deleteDoctor(selectedDoctor.doctor!!)

            }

            clearFields()

        }
        binding.editButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""
            if (nameStr.isNotEmpty() && selectedSpeciality != null) {
                Doctor(
                    name = nameStr.toString(),
                    speciality = selectedSpeciality!!.id
                ).let {
                    viewModel.updateDoctor(it)
//                    clearFields()
                }
            }
        }
    }

    private fun setupSpinnerSpeciality(specialities: List<Speciality>) {

        val adapterSpinner =
            ArrayAdapter(
                requireContext(),
                R.layout.spinner_item_gender,
                specialities
            )

        val autoCompleteBrand: AutoCompleteTextView? =
            binding.inputSpecialityTextInputLayout.editText as? AutoCompleteTextView

        autoCompleteBrand?.threshold = 1

        autoCompleteBrand?.setAdapter(adapterSpinner)

        autoCompleteBrand?.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as Speciality
            selectedSpeciality = selected
        }

    }

    fun clearFields() {
        binding.inputNameTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.editText?.setText("")
        binding.inputIdTextInputLayout.visibility = View.GONE
        binding.newButton.visibility = View.VISIBLE

        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)

        selectedSpeciality = null
    }

    fun setValueToFields(doctorPOJO: DoctorPOJO) {
        binding.inputIdTextInputLayout.editText?.setText(doctorPOJO.doctor?.id.toString())
        binding.inputNameTextInputLayout.editText?.setText(doctorPOJO.doctor?.name)

        binding.inputIdTextInputLayout.visibility = View.VISIBLE
        binding.newButton.visibility = View.GONE

        selectedSpeciality = doctorPOJO.speciality
    }

}