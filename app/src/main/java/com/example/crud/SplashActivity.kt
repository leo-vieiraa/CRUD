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
        setContentView(R.layout.activity_splash)

        val job = launch {
            toNextActivity()
        }
    }

    private suspend fun toNextActivity() {

        val intent = Intent(this, MainActivity::class.java)
        delay(3000)
        withContext(Dispatchers.Main){
            startActivity(intent)
        }

    }

}