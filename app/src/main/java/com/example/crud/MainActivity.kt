package com.example.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud.view.PacienteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PacienteFragment.newInstance())
            .commitNow()
    }
}