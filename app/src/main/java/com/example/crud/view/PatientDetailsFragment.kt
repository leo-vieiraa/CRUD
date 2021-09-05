package com.example.crud.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crud.R
import com.example.crud.databinding.FragmentPatientDetailsBinding
import com.example.crud.model.Patient
import java.io.Serializable

class PatientDetailsFragment : Fragment(R.layout.fragment_patient_details) {

    companion object {

        fun newInstance() = PatientDetailsFragment()

//            PatientDetailsFragment().apply {
//                arguments = Bundle().apply {
//                    putSerializable("patient", patient)
//                }
//            }
    }

    private lateinit var binding : FragmentPatientDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments?.getSerializable("patient") as Patient

        binding = FragmentPatientDetailsBinding.bind(view)
        binding.patientNameTextView.text = arguments.name
        binding.patientAgeTextView.text = arguments.age.toString()
        binding.patientGenderTextView.text = arguments.gender.toString()

    }

}