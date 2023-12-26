package com.example.betsim.presentation.tournaments_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


data class Tournament(
    val name: String
)

data class TournamentsState(
    val tournaments: List<Tournament> = emptyList()
)

@HiltViewModel
class TournamentsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val isToday: Boolean

    private var _state = mutableStateOf(TournamentsState())
    val state = _state

    private var _route = mutableStateOf("")
    val route = _route

    init {
        isToday = checkNotNull(savedStateHandle["today"])
        _route.value = if(isToday) Screen.TodayTournamentDetailScreen.route
                        else Screen.TournamentDetailScreen.route
        getTournaments()
    }

    private fun getTournaments(){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                tournaments = listOf(Tournament("Turniej"))
            )
        }
    }


}