package com.example.betsim.presentation.tournament_details_user

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject




@HiltViewModel
class TournamentDetailsViewModel @Inject constructor(

): ViewModel() {

    private var _games = mutableStateListOf<TournamentGame>()
    val games = _games


    init {
        val odd1 = Odd(1,"asd1",1.3)
        val odd2 = Odd(1,"asd2",1.7)
        val odd3 = Odd(1,"asd3",1.8)
        val game1 = TournamentGame(1,"Polska", "Mołdawia", listOf(odd1,odd2,odd3))
        val game2 = TournamentGame(2,"Polska", "Estonia", listOf(odd1,odd2,odd3))
        val game3 = TournamentGame(3,"Polska", "Estonia", listOf(odd1,odd2,odd3))
        val game4 = TournamentGame(4,"Polska", "Estonia", listOf(odd1,odd2,odd3))
        val game5 = TournamentGame(5,"Polska", "Estonia", listOf(odd1,odd2,odd3))
        val game6 = TournamentGame(6,"Polska", "Estonia", listOf(odd1,odd2,odd3))
        val game7 = TournamentGame(7,"Polska", "Estonia", listOf(odd1,odd2,odd3))
        val game8 = TournamentGame(8,"Polska", "Łotwa", listOf(odd1,odd2))
        _games.add(game1)
        _games.add(game2)
        _games.add(game3)
        _games.add(game4)
        _games.add(game5)
        _games.add(game6)
        _games.add(game7)
        _games.add(game8)
    }

    fun onEvent(event: TournamentDetailsEvent){
        when(event){
            is TournamentDetailsEvent.LoadList -> {
                games[event.index] = event.game
            }
            is TournamentDetailsEvent.OnSelect -> {
                event.game.selected.value = event.index
            }
        }
    }

}