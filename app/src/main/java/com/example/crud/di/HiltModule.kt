package com.example.crud.di

import android.content.Context
import com.example.crud.database.AppDatabase
import com.example.crud.database.dao.DoctorDAO
import com.example.crud.database.dao.PatientDAO
import com.example.crud.database.dao.SpecialityDAO
import com.example.crud.repository.DoctorRepository
import com.example.crud.repository.PatientRepository
import com.example.crud.repository.SpecialityRepository
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
        return AppDatabase.getDatabase(context).getPatientDao()
    }

    @Provides
    fun provideRepositoryPatient(patientDAO: PatientDAO): PatientRepository = PatientRepository(patientDAO)

    @Provides
    fun provideContextDoctor(@ApplicationContext context: Context): DoctorDAO {
        return AppDatabase.getDatabase(context).getDoctorDao()
    }

    @Provides
    fun provideRepositoryDoctor(doctorDAO: DoctorDAO): DoctorRepository = DoctorRepository(doctorDAO)

    @Provides
    fun provideContextspeciality(@ApplicationContext context: Context): SpecialityDAO {
        return AppDatabase.getDatabase(context).getSpecialityDao()
    }

    @Provides
    fun provideRepositorySpeciality(specialityDAO: SpecialityDAO): SpecialityRepository = SpecialityRepository(specialityDAO)

}