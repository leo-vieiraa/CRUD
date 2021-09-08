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
import com.example.crud.adapter.SchedulingAdapter
import com.example.crud.databinding.SchedulingFragmentBinding
import com.example.crud.model.*
import com.example.crud.viewmodel.DoctorViewModel
import com.example.crud.viewmodel.PatientViewModel
import com.example.crud.viewmodel.SchedulingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchedulingFragment : Fragment(R.layout.scheduling_fragment) {

    companion object {
        fun newInstance() = SchedulingFragment()
    }

    private lateinit var viewModel: SchedulingViewModel
    private lateinit var viewModelPatient: PatientViewModel
    private lateinit var viewModelDoctor: DoctorViewModel
    private lateinit var binding: SchedulingFragmentBinding
    private val adapter = SchedulingAdapter()
    private val observerScheduling = Observer<List<SchedulingPOJO>> {
        adapter.refresh(it)
    }

    private var selectedPatient: Patient? = null
    private var selectedDoctor: DoctorPOJO? = null

    private val observerPatients = Observer<List<Patient>> { patient ->
        setupSpinnerPatient(patient)
    }

    private val observerDoctors = Observer<List<DoctorPOJO>> { doctor ->
        setupSpinnerDoctor(doctor)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SchedulingFragmentBinding.bind(view)
        binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.scheduleRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(SchedulingViewModel::class.java)
        viewModel.scheduling.observe(viewLifecycleOwner, observerScheduling)

        viewModelPatient = ViewModelProvider(this).get(PatientViewModel::class.java)
        viewModelPatient.patient.observe(viewLifecycleOwner, observerPatients)

        viewModelDoctor = ViewModelProvider(this).get(DoctorViewModel::class.java)
        viewModelDoctor.doctor.observe(viewLifecycleOwner, observerDoctors)

        viewModel.getSchedule()
        viewModelPatient.getPatient()
        viewModelDoctor.getDoctor()

        setupForm()

    }

    fun setupForm() {

        binding.newButton.setOnClickListener {

            if (selectedPatient != null && selectedDoctor != null) {
                Scheduling(
                    patientFK = selectedPatient!!.id,
                    doctorFK = selectedDoctor!!.doctor!!.id,
                ).let {
                    viewModel.insert(it)
                    clearFields()
                }
            }
        }
        binding.editButton.setOnClickListener {

            if (selectedPatient != null && selectedDoctor != null) {
                Scheduling(
                    patientFK = selectedPatient!!.id,
                    doctorFK = selectedDoctor!!.doctor!!.id,
                ).let {
                    viewModel.update(it)
                    clearFields()
                }
            }
        }
    }

    fun setupSpinnerPatient(patient: List<Patient>) {

        val adapterSpinner =
            ArrayAdapter(
                requireContext(),
                R.layout.spinner_item_patient,
                patient
            )

        val autoCompleteBrand: AutoCompleteTextView? =
            binding.inputPatientNameTextInputLayout.editText as? AutoCompleteTextView

        autoCompleteBrand?.threshold = 1

        autoCompleteBrand?.setAdapter(adapterSpinner)

        autoCompleteBrand?.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as Patient
            selectedPatient = selected
        }


    }

    fun setupSpinnerDoctor(doctor: List<DoctorPOJO>) {

        val adapterSpinner =
            ArrayAdapter(
                requireContext(),
                R.layout.spinner_item_doctor,
                doctor
            )

        val autoCompleteBrand: AutoCompleteTextView? =
            binding.inputDoctorNameTextInputLayout.editText as? AutoCompleteTextView

        autoCompleteBrand?.threshold = 1

        autoCompleteBrand?.setAdapter(adapterSpinner)

        autoCompleteBrand?.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as DoctorPOJO
            selectedDoctor = selected
        }
    }

    fun clearFields() {
        binding.inputPatientNameTextInputLayout.editText?.setText("")
        binding.inputDoctorNameTextInputLayout.editText?.setText("")
        binding.newButton.visibility = View.VISIBLE

        selectedPatient = null
        selectedDoctor = null
    }

}