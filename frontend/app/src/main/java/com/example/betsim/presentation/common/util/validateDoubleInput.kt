package com.example.betsim.presentation.common.util

fun validateDoubleInput(value: String, maxValue: Double) : String?{
    var new = value.trim()
    if (new.isEmpty()) return "0"
    new = new.replace(',', '.')
    val num = new.toDoubleOrNull() ?: return null
    if (num < 0) return null
    if (num > maxValue) return maxValue.toString()

    val split = new.split(".")


    if (split.size == 2){
        val left = split[0]
        val right = split[1]

        if (right.length > 2) return null

        if (left.isEmpty() && right.isEmpty()) return "0,"

        if (left.isEmpty()) return "0,$right"

        if (right.isEmpty()) return "${left.toInt()},"

        return "${left.toInt()},$right"
    }

    return new.toInt().toString()

}