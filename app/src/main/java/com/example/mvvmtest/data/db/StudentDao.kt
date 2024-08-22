package com.example.mvvmtest.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mvvmtest.data.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM student_table")
    fun getAllStudents(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllStudents()

}