package com.ttec.section23.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.ttec.section23.addtasks.data.TaskDao
import com.ttec.section23.addtasks.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTaskDataBase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(appContext, TaskDatabase::class.java, "TaskDatabase").build()
    }
}