package com.example.crud.di

import android.content.Context
import com.example.crud.database.AppDatabase
import com.example.crud.database.dao.PatientDAO
import com.example.crud.repository.PatientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideContextPatient(@ApplicationContext context: Context): PatientDAO {
        return AppDatabase.getDatabase(context).pacientDao()
    }

    @Provides
    fun provideRepository(patientDAO: PatientDAO): PatientRepository = PatientRepository(patientDAO)

}