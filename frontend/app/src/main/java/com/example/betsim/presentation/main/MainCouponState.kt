package com.example.betsim.presentation.main

import com.example.betsim.domain.model.TournamentGame
import com.example.betsim.presentation.common.util.TextFieldState

data class MainCouponState (
    val games: List<TournamentGame> = emptyList(),
    val betValue: TextFieldState<String> = TextFieldState("0"),
    val winnings: String = "",
    val oddValue: Double = 0.0,
    val hidden: Boolean = true,
    val collapsed: Boolean = true,
)