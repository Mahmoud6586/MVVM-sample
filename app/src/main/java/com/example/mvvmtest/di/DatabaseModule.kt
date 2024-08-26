package com.example.mvvmtest.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmtest.Utils.Constants
import com.example.mvvmtest.Utils.Constants.DATABASE_NAME
import com.example.mvvmtest.data.db.SchoolDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Creating a singleton instance of DB
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, SchoolDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: SchoolDatabase) = database.studentDao()

}


