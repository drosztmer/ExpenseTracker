package com.drosztmer.expensetracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.drosztmer.expensetracker.data.Repository
import com.drosztmer.expensetracker.data.model.Expense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    val getAllExpenses: LiveData<List<Expense>> = repository.local.readExpenses().asLiveData()

    fun insertExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertExpense(expense)
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.updateExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteExpense(expense)
        }
    }

    fun deleteAllExpenses() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteAllExpenses()
        }
    }

}