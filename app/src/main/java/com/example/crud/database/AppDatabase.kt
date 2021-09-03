package com.example.crud.database

import android.content.Context
import androidx.room.*
import com.example.crud.database.dao.PatientDAO
import com.example.crud.model.Speciality
import com.example.crud.model.Doctor
import com.example.crud.model.Patient

@Database(
    entities = [Patient::class, Doctor::class, Speciality::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun pacientDao() : PatientDAO

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