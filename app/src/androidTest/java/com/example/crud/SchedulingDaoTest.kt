package com.example.crud

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.crud.database.AppDatabase
import com.example.crud.database.dao.DoctorDAO
import com.example.crud.database.dao.PatientDAO
import com.example.crud.database.dao.SchedulingDAO
import com.example.crud.database.dao.SpecialityDAO
import com.example.crud.model.*
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SchedulingDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var daoDoctor: DoctorDAO
    private lateinit var daoPatient: PatientDAO
    private lateinit var daoScheduling: SchedulingDAO
    private lateinit var daoSpeciality: SpecialityDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        daoPatient = database.getPatientDao()
        daoScheduling = database.getSchedulingDao()
        daoDoctor = database.getDoctorDao()
        daoSpeciality = database.getSpecialityDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun testing_insert() {
        val pa1 = Patient(1, "pa1", 22, Gender.MALE)
        val pa2 = Patient(2, "pa1", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)


        val results = daoScheduling.fetch()
        assertThat(results).hasSize(listToInsert.size)
    }

    @Test
    fun testing_fetch_by_physician_name() {
        val pa1 = Patient(1, "pa1", 22, Gender.MALE)
        val pa2 = Patient(2, "pa1", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByPhysician("ime")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_physician_specialities() {
        val pa1 = Patient(1, "pa1", 22, Gender.MALE)
        val pa2 = Patient(2, "pa1", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByPhysicianSpeciality(listOf(1,5,7))
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_patient_name() {
        val pa1 = Patient(1, "Joao", 22, Gender.MALE)
        val pa2 = Patient(2, "Maria", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByPatient("ria")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_gender_name() {
        val pa1 = Patient(1, "Joao", 22, Gender.MALE)
        val pa2 = Patient(2, "Maria", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByGender("F")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_id() {
        val pa1 = Patient(1, "pa1", 22, Gender.MALE)
        val pa2 = Patient(2, "pa1", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val result = daoScheduling.fetch(1)
        assertThat(result.doctor.name).isEqualTo(doc1.name)
        assertThat(result.patient.name).isEqualTo(pa1.name)
    }

    @Test
    fun testing_update() {
        val pa1 = Patient(1, "pa1", 22, Gender.MALE)
        val pa2 = Patient(2, "pa1", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val forUpdate = Scheduling(
            id = sche1.id,
            doctorFK = doc2.id,
            patientFK = pa2.id,
        )
        daoScheduling.update(forUpdate)

        val result = daoScheduling.fetch(sche1.id)
//        assertThat(result.physician.physician?.name).isEqualTo(doc2.name)
        assertThat(result.patient.name).isEqualTo(pa2.name)
    }

    @Test
    fun testing_delete() {
        val pa1 = Patient(1, "pa1", 22, Gender.MALE)
        val pa2 = Patient(2, "pa1", 22, Gender.MALE)
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val doc1 = Doctor(id = 1, name = "Jaime", speciality = s2.id)
        val doc2 = Doctor(id = 2, name = "Botao", speciality = s1.id)
        daoDoctor.insert(listOf(doc1, doc2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, doctorFK = doc1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, doctorFK = doc2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        daoScheduling.delete(sche1)

        val result = daoScheduling.fetch()
        assertThat(result).doesNotContain(sche1)
    }
}