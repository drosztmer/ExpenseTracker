package com.drosztmer.expensetracker.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

// Entity for the table
@Entity(tableName = "expense_table")
@Parcelize
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var timeCreated: String,
    var title: String,
    var price: BigDecimal
): Parcelable
