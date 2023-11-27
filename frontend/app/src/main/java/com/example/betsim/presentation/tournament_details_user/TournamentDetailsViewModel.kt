package com.example.betsim.presentation.tournament_details_user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



data class TournamentGame(
    var homeTeam: String,
    var awayTeam: String,
    var odds: List<Float>,
    var selected: MutableState<Int?> = mutableStateOf(null)
)

data class TournamentGamesState(
    var games: List<TournamentGame> = emptyList()
)

@HiltViewModel
class TournamentDetailsViewModel @Inject constructor(

): ViewModel() {

    private var _state = mutableStateOf(TournamentGamesState())
    val state = _state

    init {
        val game1 = TournamentGame("Polska", "Mołdawia", listOf(1.5f,1.8f,1.2f))
        val game2 = TournamentGame("Polska", "Estonia", listOf(1.5f,1.8f,1.2f))
        val game3 = TournamentGame("Polska", "Łotwa", listOf(1.5f,1.8f))
        _state.value.games = listOf(game1,game2,game3)
    }

}