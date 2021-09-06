package com.example.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crud.R
import com.example.crud.viewmodel.SpecialityViewModel

class SpecialityFragment : Fragment(R.layout.speciality_fragment) {

    companion object {
        fun newInstance() = SpecialityFragment()
    }

    private lateinit var viewModel: SpecialityViewModel

}