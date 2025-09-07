package com.example.secondhandcars.utils

import com.example.secondhandcars.models.Car
import java.text.NumberFormat

class Formatter {
    val format = NumberFormat.getCurrencyInstance()

    init {
        format.maximumFractionDigits = 2
        format.currency = java.util.Currency.getInstance("EUR")
    }

    fun formatDouble(value: Double): String {
        return format.format(value)
    }
}