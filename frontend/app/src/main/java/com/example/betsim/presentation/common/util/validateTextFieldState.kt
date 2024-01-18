package com.example.betsim.presentation.common.util

fun <T>validateTextFieldState(textFieldState: TextFieldState<T>): TextFieldState<T>{

    if (textFieldState.value == null || textFieldState.value.toString().isBlank())
        return textFieldState.copy(isError = true, errorText = "Pole nie może być puste")

    if (textFieldState.isError)
        return textFieldState.copy(isError = false, errorText = "")

    return textFieldState

}