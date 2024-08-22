package com.example.mvvmtest.data.repository

import com.example.mvvmtest.data.db.StudentDao
import com.example.mvvmtest.data.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun addNewStudent(student: Student) {
        studentDao.addNewStudent(student)
    }

    suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }

    suspend fun deleteAllStudents() {
        studentDao.deleteAllStudents()
    }
}