package com.drosztmer.expensetracker.data

import com.drosztmer.expensetracker.data.database.ExpenseDao
import com.drosztmer.expensetracker.data.model.Expense
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val expenseDao: ExpenseDao
) {

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }

    fun readExpenses(): Flow<List<Expense>> {
        return expenseDao.readExpenses()
    }

    suspend fun deleteAllExpenses() {
        expenseDao.deleteAllExpenses()
    }
}