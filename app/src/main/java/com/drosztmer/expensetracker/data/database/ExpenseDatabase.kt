package com.drosztmer.expensetracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.drosztmer.expensetracker.data.model.Expense

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase: RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

}