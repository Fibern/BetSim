package com.example.betsim.data.remote.responses

import java.time.LocalDateTime

data class Coupon(
    val bets: List<Bet>,
    val dateTime: LocalDateTime,
    val oddSum: Double,
    val value: Double
)