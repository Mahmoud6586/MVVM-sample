package com.example.mvvmtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmtest.data.db.SchoolDatabase
import com.example.mvvmtest.data.model.Student
import com.example.mvvmtest.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentsViewModel(application: Application) : AndroidViewModel(application) {

    val allStudents: Flow<List<Student>>
    private val repository: StudentRepository

    init {
        val studentDao = SchoolDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
    }

    fun addNewStudent(student: Student) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student)
        }
    }
}