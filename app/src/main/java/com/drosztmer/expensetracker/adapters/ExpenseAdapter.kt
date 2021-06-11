package com.drosztmer.expensetracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.drosztmer.expensetracker.data.model.Expense
import com.drosztmer.expensetracker.databinding.ListItemBinding

// Adapter to create viewholders and connects them with the appropriate data
class ExpenseAdapter: RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>() {

    var expenseList = emptyList<Expense>()

    class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // Function to connect data with views by setting data binding variables
        fun bind(expense: Expense) {
            binding.time = expense.timeCreated
            binding.title = expense.title
            binding.price = expense.price.toString() + " Ft"
            binding.executePendingBindings()
        }
    }

    // Creates viewholder and inflates layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    // Sets viewholder's data by it's position
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentExpense = expenseList[position]
        holder.bind(currentExpense)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    // Function for setting data to show in recyclerview
    fun setData(newList: List<Expense>) {
        // Use DiffUtil for better performance
        val expenseDiffUtil = ExpenseDiffUtil(expenseList, newList)
        val expenseDiffResult = DiffUtil.calculateDiff(expenseDiffUtil)
        expenseList = newList
        expenseDiffResult.dispatchUpdatesTo(this)
    }

}