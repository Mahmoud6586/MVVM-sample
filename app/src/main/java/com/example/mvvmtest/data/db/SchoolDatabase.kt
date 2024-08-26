package com.example.mvvmtest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmtest.Utils.Constants.DATABASE_NAME
import com.example.mvvmtest.data.model.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao




}