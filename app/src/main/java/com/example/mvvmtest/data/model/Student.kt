package com.example.mvvmtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmtest.Utils.Constants.STUDENT_TABLE


@Entity(tableName = STUDENT_TABLE)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val family: String,
    val nationalCode: String,
    val grade: Grade

)
