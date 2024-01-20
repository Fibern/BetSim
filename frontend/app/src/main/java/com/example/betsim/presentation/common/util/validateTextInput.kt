package com.example.betsim.presentation.common.util

fun validateTextInput(text: String): Boolean {
    if(text.isEmpty()) return true
    val regex = Regex("\\S+")
    return regex.matches(text.trim())
}