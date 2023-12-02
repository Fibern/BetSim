package com.example.betsim.presentation.user_main

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.TournamentGame
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserMainViewModel @Inject constructor(

) : ViewModel() {

    private var _games = mutableStateListOf<TournamentGame>()
    val games = _games

    private val _oddValue = mutableDoubleStateOf(0.0)
    val oddValue = _oddValue


    fun onEvent(event: UserMainEvent){
        when(event){
            is UserMainEvent.EnteredValue -> {
                TODO()
            }
            is UserMainEvent.MakeBet -> {
                TODO()
            }
            is UserMainEvent.DeleteGame -> {
                _games.remove(event.game)
                event.game.selected.value = null
                //TODO()
            }
            is UserMainEvent.AddGame -> {
                val odd = event.game.odds[event.game.selected.value!!].odd
                if(_oddValue.doubleValue == 0.0){
                    _oddValue.doubleValue = odd
                }else{
                    _oddValue.doubleValue *= odd
                }
            }
        }
    }


}