package com.example.betsim.presentation.common.util

data class TextFieldState<T>(
    val value: T,
    val isError: Boolean = false,
    val errorText: String = ""
)
