package com.drosztmer.expensetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.drosztmer.expensetracker.data.model.Expense
import com.drosztmer.expensetracker.databinding.ListItemBinding

class ExpenseAdapter: RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>() {

    var expenseList = emptyList<Expense>()

    class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense) {
            binding.expense = expense
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentExpense = expenseList[position]
        holder.bind(currentExpense)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun setData(newList: List<Expense>) {
        val expenseDiffUtil = ExpenseDiffUtil(expenseList, newList)
        val expenseDiffResult = DiffUtil.calculateDiff(expenseDiffUtil)
        expenseList = newList
        expenseDiffResult.dispatchUpdatesTo(this)
    }

}