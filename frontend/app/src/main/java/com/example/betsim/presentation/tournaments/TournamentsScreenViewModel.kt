package com.example.betsim.presentation.tournaments

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.domain.model.Tournament
import com.example.betsim.presentation.util.Screen
import com.example.betsim.domain.model.EventIcons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TournamentsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isToday = mutableStateOf(false)

    private val _isMod = mutableStateOf(false)
    val isMod: State<Boolean> = _isMod

    private var _state = mutableStateOf(TournamentsState())
    val state: State<TournamentsState> = _state

    private var _route = mutableStateOf("")
    val route: State<String> = _route

    init {
        _isToday.value = checkNotNull(savedStateHandle["today"])
        _isMod.value = checkNotNull(savedStateHandle["mod"])
        if (_isToday.value) _route.value = Screen.TodayTournamentDetailScreen.route
        else _route.value = Screen.TournamentDetailScreen.route
        getTournaments()
    }

    private fun getTournaments(){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                tournaments = listOf(Tournament("Turniej", EventIcons.Football))
            )
        }
    }


}