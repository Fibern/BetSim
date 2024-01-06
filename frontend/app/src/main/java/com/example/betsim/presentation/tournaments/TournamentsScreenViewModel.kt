package com.example.betsim.presentation.tournaments

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.domain.model.Tournament
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.common.data.EventIcons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TournamentsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val isToday: Boolean
    private val isMod: Boolean

    private var _state = mutableStateOf(TournamentsState())
    val state = _state

    private var _route = mutableStateOf("")
    val route = _route

    init {
        isToday = checkNotNull(savedStateHandle["today"])
        isMod = checkNotNull(savedStateHandle["mod"])
        _route.value = Screen.TournamentDetailScreen.route
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