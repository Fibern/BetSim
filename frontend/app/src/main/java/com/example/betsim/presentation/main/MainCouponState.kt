package com.example.betsim.presentation.main

import com.example.betsim.domain.model.TournamentGame

data class MainCouponState (
    val games: List<TournamentGame> = emptyList(),
    val value: String = "0",
    val winnings: Double = 0.0,
    val oddValue: Double = 0.0,
    val hidden: Boolean = true,
    val collapsed: Boolean = true,
)