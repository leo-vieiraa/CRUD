package com.example.crud

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.crud.database.AppDatabase
import com.example.crud.database.dao.DoctorDAO
import com.example.crud.database.dao.SpecialityDAO
import com.example.crud.model.Doctor
import com.example.crud.model.Speciality
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PhysicianDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var daoDoctor: DoctorDAO
    private lateinit var daoSpeciality: SpecialityDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        daoSpeciality = database.getSpecialityDao()
        daoDoctor = database.getDoctorDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun testing_insert() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(name = "Jaime", speciality = s2.id)
        val p2 = Doctor(name = "Jaime", speciality = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoDoctor.insert(listToInsert)

        val results = daoDoctor.fetch()
        assertThat(results).hasSize(listToInsert.size)
    }

    @Test
    fun testing_fetch_by_name() {

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(name = "Jaime", speciality = s2.id)
        val p2 = Doctor(name = "Botao", speciality = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoDoctor.insert(listToInsert)
        val results = daoDoctor.fetch("ao")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_id() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val p2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoDoctor.insert(listToInsert)

        val result = daoDoctor.fetch(2)
        assertThat(result.doctor?.name).isEqualTo(p2.name)
        assertThat(result.speciality?.name).isEqualTo(s1.name)
    }

    @Test
    fun testing_update() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val p2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoDoctor.insert(listToInsert)

        val forUpdate = Doctor(
            id = p2.id,
            name = "Phy2Updated",
            speciality = s2.id
        )
        daoDoctor.update(forUpdate)

        val result = daoDoctor.fetch(p2.id)
        assertThat(result.doctor?.name).isEqualTo(forUpdate.name)
        assertThat(result.doctor?.speciality).isEqualTo(forUpdate.speciality)
    }

    @Test
    fun testing_delete() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val p2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoDoctor.insert(listToInsert)

        daoDoctor.delete(p1)

        val result = daoDoctor.fetch()
        assertThat(result).doesNotContain(p1)
    }
}