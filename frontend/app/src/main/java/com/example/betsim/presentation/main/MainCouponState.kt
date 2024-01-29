package com.example.betsim.presentation.main

import com.example.betsim.data.remote.responses.Offer
import com.example.betsim.presentation.common.util.TextFieldState

data class MainCouponState (
    val offers: List<Offer> = emptyList(),
    val betValue: TextFieldState<String> = TextFieldState("0"),
    val winnings: String = "",
    val oddValue: Double = 0.0,
    val hidden: Boolean = true,
    val collapsed: Boolean = true,
)