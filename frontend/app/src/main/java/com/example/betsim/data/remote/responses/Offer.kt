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
    var selected: Int? = null,
    val title: String
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Offer) return false
        if (other.id == id) return true
        return false
    }

    override fun hashCode(): Int {
        var result = active.hashCode()
        result = 31 * result + dateTime.hashCode()
        result = 31 * result + id
        result = 31 * result + odds.hashCode()
        result = 31 * result + score.hashCode()
        result = 31 * result + type
        result = 31 * result + winner
        result = 31 * result + (selected ?: 0)
        result = 31 * result + title.hashCode()
        return result
    }


}