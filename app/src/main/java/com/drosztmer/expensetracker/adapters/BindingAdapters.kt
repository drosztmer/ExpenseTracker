package com.drosztmer.expensetracker.adapters

import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.drosztmer.expensetracker.data.model.Expense
import com.drosztmer.expensetracker.ui.fragments.HomeFragmentDirections

object BindingAdapters {

    @BindingAdapter("android:emptyDatabase")
    @JvmStatic
    fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
        when (emptyDatabase.value) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.INVISIBLE
        }
    }

    @BindingAdapter("android:sendDataToUpdateFragment")
    @JvmStatic
    fun sendDataToUpdateFragment(view: ConstraintLayout, currentItem: Expense) {
        view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateExpenseFragment(currentItem)
            view.findNavController().navigate(action)
        }
    }

}