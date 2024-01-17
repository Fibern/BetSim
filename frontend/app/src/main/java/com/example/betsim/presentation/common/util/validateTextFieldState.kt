package com.example.betsim.presentation.common.util

fun <T>validateTextFieldState(textFieldState: TextFieldState<T>): TextFieldState<T>?{
    if (textFieldState.value == null || textFieldState.value.toString().isBlank()){
        return if (textFieldState.isError){
            null
        }else{
            textFieldState.copy(isError = true, errorText = "Pole nie może być puste")
        }
    }

    return if (textFieldState.isError){
        textFieldState.copy(isError = false, errorText = "")
    }else{
        null
    }

}