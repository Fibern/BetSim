package com.example.betsim.domain.model

import java.util.Date

data class Coupon(
    val id: Int,
    val games: List<TournamentGame>,
    val finished: Boolean,
    val odd: Double,
    val betValue: Double,
    val winnings: Double,
    val date: Date = Date()
)
