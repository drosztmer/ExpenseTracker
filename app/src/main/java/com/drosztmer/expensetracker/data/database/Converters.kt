package com.drosztmer.expensetracker.data.database

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun fromPrice(price: BigDecimal): String {
        return price.toPlainString()
    }

    @TypeConverter
    fun toPrice(price: String): BigDecimal {
        return BigDecimal(price)
    }
}