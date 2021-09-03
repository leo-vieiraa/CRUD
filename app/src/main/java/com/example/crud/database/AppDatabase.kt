package com.example.crud.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.crud.CRUDApplication
import com.example.crud.database.dao.PacienteDAO
import com.example.crud.model.Especialidade
import com.example.crud.model.Medico
import com.example.crud.model.Paciente

@Database(
    entities = [Paciente::class, Medico::class, Especialidade::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun pacienteDao() : PacienteDAO

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_db"
            )
                .allowMainThreadQueries() // Utilizar esta linha quando NÃO utilizar couroutines
                .build()
        }

    }

}