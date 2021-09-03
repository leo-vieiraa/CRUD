package com.example.crud.di

import android.content.Context
import com.example.crud.database.AppDatabase
import com.example.crud.database.dao.PacienteDAO
import com.example.crud.repository.PacienteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideContextPaciente(@ApplicationContext context: Context): PacienteDAO {
        return AppDatabase.getDatabase(context).pacienteDao()
    }

    @Provides
    fun provideRepository(pacienteDAO: PacienteDAO): PacienteRepository = PacienteRepository(pacienteDAO)

}