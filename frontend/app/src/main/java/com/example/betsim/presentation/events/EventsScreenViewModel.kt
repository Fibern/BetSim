package com.example.betsim.presentation.events

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.SessionManager
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
    private val repository: BetSimRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _isToday = mutableStateOf(false)

    private val _isMod = mutableStateOf(false)
    val isMod: State<Boolean> = _isMod

    private var _events = mutableStateListOf<Event>()
    val events: List<Event> = _events

    private var _route = mutableStateOf("")
    val route: State<String> = _route

    private val _isLoading = mutableStateOf(true)
    val isLoading = _isLoading


    init {
        _isToday.value = checkNotNull(savedStateHandle["today"])
        _isMod.value = checkNotNull(savedStateHandle["mod"])
        if (_isToday.value) _route.value = Screen.TodayTournamentDetailScreen.route
        else _route.value = Screen.TournamentDetailScreen.route

        if (_isMod.value) getModEvents()
        else getEvents()

        _isLoading.value = false
    }

    private fun getEvents(){
        viewModelScope.launch {
            _isLoading.value = true
            when(val response = repository.getEvents()){
                BasicStatus.BadInternet -> {
                    //TODO()
                }
                BasicStatus.Failure -> {
                    //TODO()
                }
                is BasicStatus.Success -> {
                    _events.clear()
                    _events.addAll(response.response.events)
                }
            }
            _isLoading.value = false
        }
    }

    private fun getModEvents(){
        viewModelScope.launch {
            _isLoading.value = true
            val login = sessionManager.getCurrent()
            if (login != null) {
                when (val response = repository.getEventsByUser(login.accessToken)) {
                    BasicStatus.BadInternet -> {
                        Log.i("jd", "d")

                    }
                    BasicStatus.Failure -> {
                        Log.i("jd", "d")

                    }
                    is BasicStatus.Success -> {
                        _events.clear()
                        _events.addAll(response.response.events)
                    }
                }
            }
            _isLoading.value = false
        }
    }


    private fun deleteEvent(id: Int){
        viewModelScope.launch {
            _isLoading.value = true
            val login = sessionManager.getCurrent()
            if (login != null) {
                if (repository.deleteEvent(login.accessToken, id)) {
                    getModEvents()
                }else{
                    //TODO()
                }
            }
            _isLoading.value = false
        }
    }

    fun onEvent(event: EventsScreenEvent){
        when(event){
            is EventsScreenEvent.DeleteClicked -> {
                deleteEvent(event.id)
            }
        }
    }


}