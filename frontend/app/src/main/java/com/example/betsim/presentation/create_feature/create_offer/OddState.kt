package com.example.betsim.presentation.create_feature.create_offer

import com.example.betsim.presentation.common.util.TextFieldState

data class OddState(
    val name: TextFieldState<String> = TextFieldState(value = ""),
    val odd: TextFieldState<String> = TextFieldState(value = "")
)