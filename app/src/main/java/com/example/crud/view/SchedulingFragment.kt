package com.example.crud.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crud.R
import com.example.crud.viewmodel.SchedulingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchedulingFragment : Fragment() {

    companion object {
        fun newInstance() = SchedulingFragment()
    }

    private lateinit var viewModel: SchedulingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scheduling_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SchedulingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}