package com.example.betsim.data.remote.responses

data class Offer(
    val active: Boolean,
    val dateTime: String,
    val id: Int,
    val odds: List<Odd>,
    val score: String,
    val type: Int,
    val winner: Int
)