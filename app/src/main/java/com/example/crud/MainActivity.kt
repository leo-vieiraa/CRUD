package com.example.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud.databinding.ActivityMainBinding
import com.example.crud.utils.replaceFragment
import com.example.crud.view.DoctorFragment
import com.example.crud.view.PatientFragment
import com.example.crud.view.SpecialityFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        replaceFragment(PatientFragment.newInstance())

        binding.bottomNav.apply {

            setOnItemSelectedListener {

                when(it.itemId) {
                    R.id.nav_patients -> replaceFragment(PatientFragment.newInstance())
                    R.id.nav_specialities -> replaceFragment(SpecialityFragment.newInstance())
                    R.id.nav_doctors -> replaceFragment(DoctorFragment.newInstance())
                }
                true

            }

        }
    }
}