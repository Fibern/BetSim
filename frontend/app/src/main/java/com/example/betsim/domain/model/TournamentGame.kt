package com.example.betsim.domain.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TournamentGame(
    var id: Int,
    var homeTeam: String,
    var awayTeam: String,
    var odds: List<Float>,
    var selected: MutableState<Int?> = mutableStateOf(null)
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

