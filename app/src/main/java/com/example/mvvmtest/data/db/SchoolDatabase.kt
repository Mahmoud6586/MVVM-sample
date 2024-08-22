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


    companion object {

        @Volatile // volatile: to prevent multiple threads call this object simultaneously
        private var INSTANCE: SchoolDatabase? = null


        fun getDatabase(context: Context): SchoolDatabase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}