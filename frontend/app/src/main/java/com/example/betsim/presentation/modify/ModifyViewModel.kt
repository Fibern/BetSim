package com.example.betsim.presentation.modify

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.OfferType
import com.example.betsim.domain.model.TournamentGame
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateDoubleInput
import com.example.betsim.presentation.common.util.validateTextFieldState
import javax.inject.Inject

class ModifyViewModel @Inject constructor(

): ViewModel() {

    private val _game = mutableStateOf(TournamentGame())
    val game: State<TournamentGame> = _game

    private val _home = mutableStateOf(TextFieldState(""))
    val home: State<TextFieldState<String>> = _home

    private val _away = mutableStateOf(TextFieldState(""))
    val away: State<TextFieldState<String>> = _away

    private val _selected = mutableStateOf(TextFieldState<Int?>(null))
    val selected: State<TextFieldState<Int?>> = _selected

    init {
        getTournamentGame()
    }

    private fun getTournamentGame() {
        _game.value = TournamentGame(
            1,
            "team 1 - team 2",
            listOf(Odd(0,"team 1"), Odd(0, "team 2")),
        )
    }

    fun onEvent(event: ModifyEvent){
        when(event){
            is ModifyEvent.OptionChanged -> {
                _selected.value = _selected.value.copy(value = event.id)
            }
            is ModifyEvent.ScoreEntered -> {
                val value = validateDoubleInput(event.score, 1000.0) ?: return
                if (event.home) _home.value = _home.value.copy(value = value)
                else _away.value = _away.value.copy(value = value)
            }
            ModifyEvent.ConfirmClick -> {
                when(_game.value.type){
                    OfferType.Match -> {
                        _home.value = validateTextFieldState(_home.value) ?: _home.value
                        _away.value = validateTextFieldState(_away.value) ?: _away.value
                    }
                    OfferType.Selection -> {
                        _selected.value = validateTextFieldState(_selected.value) ?: _selected.value
                    }
                }
                //TODO()
            }
        }
    }

}