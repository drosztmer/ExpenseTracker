package com.drosztmer.expensetracker.di

import android.content.Context
import androidx.room.Room
import com.drosztmer.expensetracker.data.database.ExpenseDatabase
import com.drosztmer.expensetracker.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Module for setting up dependencies for injection
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ExpenseDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: ExpenseDatabase) = database.expenseDao()

}