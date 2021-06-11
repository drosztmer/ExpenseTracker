package com.drosztmer.expensetracker.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.drosztmer.expensetracker.data.Repository
import com.drosztmer.expensetracker.data.model.Expense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val repository: Repository,
        application: Application
) : AndroidViewModel(application) {

    val getAllExpenses: LiveData<List<Expense>> = repository.local.readExpenses().asLiveData()
    val getSum: MediatorLiveData<BigDecimal> = MediatorLiveData()

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getSum.addSource(getAllExpenses) { expenses ->
            var sum = BigDecimal.ZERO
            sum.setScale(2, RoundingMode.CEILING).stripTrailingZeros()
            expenses?.forEach { sum = sum.add(it.price) }
            getSum.postValue(sum)
        }
    }

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

    fun checkIfDatabaseEmpty(expenseList: List<Expense>) {
        emptyDatabase.value = expenseList.isEmpty()
    }

}