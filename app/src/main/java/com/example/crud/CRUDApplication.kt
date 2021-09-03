package com.example.crud

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CRUDApplication: Application() {

    companion object {
        var contextCRUDApp : Context? = null
    }


    override fun onCreate() {
        super.onCreate()

        contextCRUDApp = applicationContext


    }

}