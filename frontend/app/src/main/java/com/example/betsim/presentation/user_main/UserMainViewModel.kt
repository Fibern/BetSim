package com.example.betsim.presentation.user_main

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.TournamentGame
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class UserMainViewModel @Inject constructor(

) : ViewModel() {

    private val _mainAppBarsHidden = mutableStateOf(false)
    val mainAppBarsHidden = _mainAppBarsHidden

    private val _games = mutableStateListOf<TournamentGame>()
    val games = _games

    private val _oddValue = mutableDoubleStateOf(0.0)
    val oddValue = _oddValue

    private val _bet = mutableStateOf("0")
    val bet = _bet

    private val _couponCollapsed = mutableStateOf(true)
    val couponCollapsed = _couponCollapsed

    private val _couponHidden = mutableStateOf(true)
    val couponHidden = _couponHidden

    fun onEvent(event: UserMainEvent){
        when(event){
            is UserMainEvent.EnteredValue -> {
                _bet.value = event.value
            //TODO()
            }
            is UserMainEvent.MakeBet -> {
                TODO()
            }
            is UserMainEvent.DeleteGame -> {
                _games.remove(event.game)
                event.game.selected.value = null
                updateOdds()
            }
            is UserMainEvent.AddGame -> {
                if(event.game !in _games) _games.add(event.game)
                updateOdds()

            }
            is UserMainEvent.CollapsedChange -> {
                _couponCollapsed.value = event.collapsed
            }
            is UserMainEvent.HiddenChange -> {
                if (_games.size != 0) _couponHidden.value = event.hidden
                _couponCollapsed.value = true
            }
            is UserMainEvent.AppBarsChange -> {
                _mainAppBarsHidden.value = event.hide
            }
        }
    }

    private fun updateOdds(){
        if (_games.isEmpty()){
            _couponCollapsed.value = true
            _couponHidden.value = true
            _oddValue.doubleValue = 0.0
        }
        else {
            couponHidden.value = false
            var odd = 1.0
            _games.onEach {
                odd *= it.odds[it.selected.value!!].odd
            }
            odd = (odd * 100).roundToInt() / 100.0
            _oddValue.doubleValue = odd
        }
    }

}