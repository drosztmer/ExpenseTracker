package com.drosztmer.expensetracker.adapters

import androidx.recyclerview.widget.DiffUtil
import com.drosztmer.expensetracker.data.model.Expense

class ExpenseDiffUtil(
    private val oldList: List<Expense>,
    private val newList: List<Expense>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}