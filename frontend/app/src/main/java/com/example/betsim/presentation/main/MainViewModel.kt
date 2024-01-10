package com.example.betsim.presentation.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.presentation.common.util.validateDoubleInput
import com.example.betsim.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _modEnabled = mutableStateOf(true)
    val modEnabled = _modEnabled

    private val _mainAppBarsHidden = mutableStateOf(false)
    val mainAppBarsHidden = _mainAppBarsHidden

    private val _couponState = mutableStateOf(MainCouponState())
    val couponState = _couponState


    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.EnteredValue -> {
                val value = validateDoubleInput(event.value, 10000.0) ?: return
                val winnings = value.replace(',','.').toDouble() * _couponState.value.oddValue
                _couponState.value = _couponState.value.copy(value = value, winnings = winnings)
            }
            is MainEvent.MakeBet -> {
                TODO()
            }
            is MainEvent.DeleteGame -> {
                val tmp = _couponState.value.games - event.game
                _couponState.value = _couponState.value.copy(
                    games = tmp
                )
                event.game.selected.value = null
                updateOdds()
            }
            is MainEvent.AddGame -> {
                if (event.game in _couponState.value.games) return
                val tmp = _couponState.value.games + event.game
                _couponState.value = _couponState.value.copy(
                    games = tmp
                )
                updateOdds()

            }
            is MainEvent.CollapsedChange -> {
                _couponState.value = _couponState.value.copy(
                    collapsed = event.collapsed
                )
            }
            is MainEvent.HiddenChange -> {
                val hidden = if (_couponState.value.games.isNotEmpty()){
                    event.hidden
                }else{
                    _couponState.value.hidden
                }
                _couponState.value = _couponState.value.copy(
                    hidden = hidden,
                    collapsed = true
                )
            }

            is MainEvent.DestinationChange -> {
                _mainAppBarsHidden.value = when (event.destination) {
                    Screen.CouponDetailsScreen.route -> {
                        true
                    }
                    Screen.ModifyGameScreen.route -> {
                        true
                    }
                    Screen.AddGameMainScreen.route -> {
                        true
                    }
                    Screen.AddTournamentScreen.route -> {
                        true
                    }
                    else -> {
                        false
                    }
                }

            }
        }
    }


    private fun updateOdds(){
        if (_couponState.value.games.isEmpty()){
            _couponState.value = _couponState.value.copy(
                oddValue = 0.0,
                collapsed = true,
                hidden = true
            )
        }
        else {
            var odd = 1.0
            _couponState.value.games.onEach {
                odd *= it.odds[it.selected.value!!].odd.toDouble()
            }
            odd = (odd * 100).roundToInt() / 100.0
            _couponState.value = _couponState.value.copy(
                oddValue = odd,
                hidden = false
            )
        }
    }

}