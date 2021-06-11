package com.drosztmer.expensetracker.util

import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun createCurrentDateText(): String {
        val date = Date()
        val formatter = SimpleDateFormat("yyyy.MM.dd.")
        return formatter.format(date)
    }

    // Function to make sure that the user doesn't leave anything blank
    fun verifyDataFromUser(title: String, price: String): Boolean {
        return !(title.isEmpty() || price.isEmpty())
    }
}