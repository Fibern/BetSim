package com.example.betsim.presentation.tournament_details_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.Odd
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
        val odd1 = Odd(1,"asd1",1.3)
        val odd2 = Odd(1,"asd2",1.3)
        val odd3 = Odd(1,"asd3",1.3)
        val game1 = TournamentGame(1,"Polska", "Mołdawia", listOf(odd1,odd2,odd3))
        val game2 = TournamentGame(2,"Polska", "Estonia", listOf(odd1,odd2,odd3))
        val game3 = TournamentGame(3,"Polska", "Łotwa", listOf(odd1,odd2))
        _state.value.games = mutableListOf(game1,game2,game3)
    }

}