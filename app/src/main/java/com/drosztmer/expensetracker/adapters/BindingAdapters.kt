package com.drosztmer.expensetracker.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

object BindingAdapters {

    @BindingAdapter("android:emptyDatabase")
    @JvmStatic
    fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
        when (emptyDatabase.value) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.INVISIBLE
        }
    }

}