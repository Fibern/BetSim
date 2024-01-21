package com.example.betsim.data.remote.responses

import java.time.LocalDateTime


data class Offer(
    val active: Boolean,
    val dateTime: LocalDateTime,
    val id: Int,
    val odds: List<Odd>,
    val score: String,
    val type: Int,
    val winner: Int,
    var selected: Int? = null
)