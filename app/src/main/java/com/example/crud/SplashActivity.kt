package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crud.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(), CoroutineScope {

    lateinit var binding: ActivitySplashBinding

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val names = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")

        val retornoAsync = CoroutineScope(Dispatchers.Main).async {
            changeNames(names)
        }

        CoroutineScope(Dispatchers.Main).launch {
            val result = retornoAsync.await()
            if (result) {
                callNewActivity()
            }
        }

    }

    private suspend fun changeNames(names: Array<String>): Boolean {

        names.forEach {
            binding.nameTextView.text = it
            delay(800)
        }

        return true
    }

    private suspend fun callNewActivity() {

        val intent = Intent(this, MainActivity::class.java)
        withContext(Dispatchers.Main){
            startActivity(intent)
        }

    }

}