package com.drosztmer.expensetracker.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Entity for the table
@Entity(tableName = "expense_table")
@Parcelize
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var timeCreated: String,
    var title: String,
    var price: Int
): Parcelable
