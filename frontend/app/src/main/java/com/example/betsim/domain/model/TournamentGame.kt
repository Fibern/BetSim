package com.example.betsim.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDateTime

data class TournamentGame(
    val id: Int = 0,
    val name: String = "",
    val odds: List<Odd> = listOf(),
    val date: LocalDateTime = LocalDateTime.now(),
    val selected: MutableState<Int?> = mutableStateOf(null),
    val status: String = "awaiting",
    val type: OfferType = OfferType.Match
){
    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other !is TournamentGame) return false
        if (other.id == id) return true
        return false

    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + odds.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + selected.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}

