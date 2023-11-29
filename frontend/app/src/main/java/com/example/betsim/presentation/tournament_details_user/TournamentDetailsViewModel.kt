package com.example.betsim.presentation.tournament_details_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.TournamentGame
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



data class TournamentGamesState(
    var games: MutableList<TournamentGame> = mutableListOf()
)

@HiltViewModel
class TournamentDetailsViewModel @Inject constructor(

): ViewModel() {

    private var _state = mutableStateOf(TournamentGamesState())
    val state = _state


    init {
        val game1 = TournamentGame(1,"Polska", "Mołdawia", listOf(1.5f,1.8f,1.2f))
        val game2 = TournamentGame(2,"Polska", "Estonia", listOf(1.5f,1.8f,1.2f))
        val game3 = TournamentGame(3,"Polska", "Łotwa", listOf(1.5f,1.8f))
        _state.value.games = mutableListOf(game1,game2,game3)
    }

}