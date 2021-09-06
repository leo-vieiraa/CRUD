package com.example.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud.R
import com.example.crud.adapter.SpecialityAdapter
import com.example.crud.databinding.SpecialityFragmentBinding
import com.example.crud.model.Patient
import com.example.crud.model.Speciality
import com.example.crud.viewmodel.SpecialityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialityFragment : Fragment(R.layout.speciality_fragment) {

    companion object {
        fun newInstance() = SpecialityFragment()
    }

    private lateinit var viewModel: SpecialityViewModel
    private lateinit var binding: SpecialityFragmentBinding
    private val adapter = SpecialityAdapter() {

    }
    private val observerSpeciality = Observer<List<Speciality>> {
        adapter.refresh(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SpecialityFragmentBinding.bind(view)
        binding.specialitiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.specialitiesRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(SpecialityViewModel::class.java)
        viewModel.speciality.observe(viewLifecycleOwner, observerSpeciality)
        viewModel.getSpeciality()

        setupForm()
    }

    fun setupForm() {
        binding.newButton.setOnClickListener {
            val nameStr = binding.inputNameTextInputLayout.editText?.text ?: ""

            if (nameStr.isNotEmpty()) {
                Speciality(
                    name = nameStr.toString(),
                ).let {
                    viewModel.insertSpeciality(it)
//                    clearFields()
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

            if (nameStr.isNotEmpty()) {
                Speciality(
                    name = nameStr.toString(),
                ).let {
//                    viewModel.updatePatient(it)
//                    clearFields()
                }
            }
        }
    }

}