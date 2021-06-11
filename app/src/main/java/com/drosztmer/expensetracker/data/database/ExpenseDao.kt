package com.drosztmer.expensetracker.data.database

import androidx.room.*
import com.drosztmer.expensetracker.data.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Update
    suspend fun updateData(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY id ASC")
    fun readExpenses(): Flow<List<Expense>>

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()

    @Query("SELECT SUM(price) FROM expense_table")
    fun getSum(): Flow<Int>

}