package com.drosztmer.expensetracker.data.database

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.math.RoundingMode

class Converters {

    @TypeConverter
    fun fromPrice(price: BigDecimal): String {
        return price.toPlainString()
    }

    @TypeConverter
    fun toPrice(price: String): BigDecimal {
        return BigDecimal(price).setScale(2, RoundingMode.CEILING).stripTrailingZeros()
    }
}