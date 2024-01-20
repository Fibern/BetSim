package com.example.betsim.presentation.events

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.remote.responses.Event
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.presentation.util.Screen
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: BetSimRepository
) : ViewModel() {

    private val _isToday = mutableStateOf(false)

    private val _isMod = mutableStateOf(false)
    val isMod: State<Boolean> = _isMod

    private var _events = mutableStateListOf<Event>()
    val events: List<Event> = _events

    private var _route = mutableStateOf("")
    val route: State<String> = _route

    init {
        _isToday.value = checkNotNull(savedStateHandle["today"])
        _isMod.value = checkNotNull(savedStateHandle["mod"])
        if (_isToday.value) _route.value = Screen.TodayTournamentDetailScreen.route
        else _route.value = Screen.TournamentDetailScreen.route
        getEvents()
    }

    private fun getEvents(){
        viewModelScope.launch {
            when(val response = repository.getEvents()){
                BasicStatus.BadInternet -> TODO()
                BasicStatus.Failure -> TODO()
                is BasicStatus.Success -> {
                    _events.addAll(response.response.events)
                }
            }
        }
    }


}