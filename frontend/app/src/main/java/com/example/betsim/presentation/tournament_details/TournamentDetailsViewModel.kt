package com.example.betsim.presentation.tournament_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject


data class GamesState(
    val games: List<TournamentGame> = emptyList()
)

@HiltViewModel
class TournamentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(GamesState())
    val state = _state

    private val isToday: Boolean

    val isMod: Boolean

    init {
        isToday = checkNotNull(savedStateHandle["today"])
        isMod = checkNotNull(savedStateHandle["mod"])
        getGames()
    }

    fun onEvent(event: TournamentDetailsEvent){
        when(event){
            is TournamentDetailsEvent.LoadList -> {
                state.value.games[event.index].selected = event.game.selected
            }
            is TournamentDetailsEvent.OnSelect -> {
                event.game.selected.value = event.index
            }
        }
    }


    private fun getGames() {
        viewModelScope.launch {
            val time = LocalDateTime.now()
            val time2 = LocalDateTime.of(2023, 12, 10, 12, 20, 0)
            val odd1 = Odd(1, "tmp1", "1.8")
            val odd2 = Odd(2, "remis", "2.3")
            val odd3 = Odd(3, "tmp2", "1.7")
            val game1 = TournamentGame(1, "tmp1", "tmp2", listOf(odd1, odd2, odd3), time)
            val game2 = TournamentGame(2, "tmp1", "tmp2", listOf(odd1, odd2, odd3), time)
            val game3 = TournamentGame(3, "tmp1", "tmp2", listOf(odd1, odd2, odd3), time)
            val game4 = TournamentGame(4, "tmp1", "tmp2", listOf(odd1, odd2, odd3), time)
            val game5 = TournamentGame(5, "tmp1", "tmp2", listOf(odd1, odd2, odd3), time2)
            val game6 = TournamentGame(6, "tmp1", "tmp2", listOf(odd1, odd2, odd3), time2)
            val game7 = TournamentGame(7, "tmp1", "tmp2", listOf(odd1, odd2, odd3), time2)
            val game8 = TournamentGame(8, "tmp1", "tmp2", listOf(odd1, odd3), time2)
            val games = listOf(game1,game2,game3,game4,game5,game6,game7,game8)
            if (isToday) {
                val jd = games.filter { it.date.toLocalDate() == LocalDate.now() }
                _state.value = _state.value.copy(
                    games = jd
                )
            }
            _state.value = _state.value.copy(
                games = games
            )

        }
    }
}