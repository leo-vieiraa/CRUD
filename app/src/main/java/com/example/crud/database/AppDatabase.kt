package com.example.crud.database

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.crud.model.Especialidade
import com.example.crud.model.Medico
import com.example.crud.model.Paciente

@Database(
    entities = [Paciente::class, Medico::class, Especialidade::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase(){



}