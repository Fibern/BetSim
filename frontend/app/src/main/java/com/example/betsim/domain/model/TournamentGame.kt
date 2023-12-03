package com.example.betsim.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Date

data class TournamentGame(
    val id: Int,
    val homeTeam: String,
    val awayTeam: String,
    val odds: List<Odd>,
    val selected: MutableState<Int?> = mutableStateOf(null),
    val date: Date = Date()
){
    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other !is TournamentGame) return false
        if (other.id == id) return true
        return false

    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + homeTeam.hashCode()
        result = 31 * result + awayTeam.hashCode()
        result = 31 * result + odds.hashCode()
        result = 31 * result + selected.hashCode()
        return result
    }
}
