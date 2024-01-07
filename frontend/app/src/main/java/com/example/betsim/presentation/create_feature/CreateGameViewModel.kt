package com.example.betsim.presentation.create_feature

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.Odd
import javax.inject.Inject

class CreateGameViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(CreateGameState())
    val state = _state

    fun onEvent(event: CreationEvent){
        when(event){
            CreationEvent.CreateClick -> {
                //TODO()
            }
            CreationEvent.AddTeam -> {
                _state.value.odds.add(
                    Odd(
                        id = _state.value.odds.size + 1,
                        name = if (_state.value.drawable) "remis"
                               else "",
                        odd = 0.0
                    )
                )
            }
            is CreationEvent.EnteredDate -> {
                _state.value = _state.value.copy(date = event.date)
            }
            is CreationEvent.EnteredTime -> {
                _state.value = _state.value.copy(time = event.time)
            }
            is CreationEvent.SelectDropdown -> {
                _state.value = _state.value.copy(
                    type = event.offerType!!,
                    odds = mutableStateListOf(
                        _state.value.odds[0],
                        _state.value.odds[1]
                    ),
                    drawable = false
                )
            }
            is CreationEvent.EnteredTeamName -> {
                _state.value.odds[event.id] = _state.value.odds[event.id].copy(name = event.name)
            }
            is CreationEvent.EnteredWinChance -> {
                _state.value.odds[event.id] = _state.value.odds[event.id].copy(odd = event.odd.toDouble())
            }
            is CreationEvent.CheckBoxChange -> {
                _state.value = _state.value.copy(drawable = event.checked)
                if (event.checked) onEvent(CreationEvent.AddTeam)
                else (onEvent(CreationEvent.RemoveTeam(2)))
            }
            is CreationEvent.RemoveTeam -> {
                if (_state.value.odds.size > 2)
                    _state.value.odds.removeAt(event.id)
            }
            else -> {}
        }
    }

}