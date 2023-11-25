package com.example.betsim.presentation.tournaments_user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private var _state = mutableStateOf<TournamentsState>(TournamentsState())
    val state = _state

    init {
        isToday = checkNotNull(savedStateHandle["today"])
        getTournaments()
    }

    private fun getTournaments(){
        viewModelScope.launch {
            _state.value = state.value.copy(
                tournaments = if(isToday) listOf(Tournament("today"))
                              else listOf(Tournament("aaaa"))
            )
        }
    }


}