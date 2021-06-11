package com.drosztmer.expensetracker.data.model

import java.math.BigDecimal
import java.math.RoundingMode

enum class Currency(
        val representation : String,
        val changeRate: BigDecimal
) {
    HUF("Ft", BigDecimal.ONE),
    EUR("Є", BigDecimal("345.36"));

    fun convertPrice(price: BigDecimal): String {
        val dividedPrice = price.divide(changeRate, 2, RoundingMode.DOWN)
        val priceString = dividedPrice.stripTrailingZeros().toPlainString()
        return "$priceString $representation"
    }
}