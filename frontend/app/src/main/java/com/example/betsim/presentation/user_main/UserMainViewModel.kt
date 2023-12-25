package com.example.betsim.presentation.user_main

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

    private val _couponState = mutableStateOf(CouponState())
    val couponState = _couponState

    private val _couponCollapsed = mutableStateOf(true)
    val couponCollapsed = _couponCollapsed

    private val _couponHidden = mutableStateOf(true)
    val couponHidden = _couponHidden

    fun onEvent(event: UserMainEvent){
        when(event){
            is UserMainEvent.EnteredValue -> {
                val value = validateBetValue(event.value) ?: return

                val winnings = value.replace(',','.').toDouble() * _couponState.value.oddValue
                _couponState.value = _couponState.value.copy(value = value, winnings = winnings)
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

    private fun validateBetValue(value: String) : String?{

        var new = value.trim()
        if (new.isEmpty()) return "0"
        new = new.replace(',', '.')
        val num = new.toDoubleOrNull() ?: return null
        if (num < 0) return null

        val split = new.split(".")

        if (split.size > 2) return null

        if (split.size == 2){
            val left = split[0]
            val right = split[1]

            if (right.length > 2 || left.length > 5) return null

            if (left.isEmpty() && right.isEmpty()) return "0,"

            if (left.isEmpty()) return "0,$right"

            if (right.isEmpty()) return "${left.toInt()},"

            return "${left.toInt()},$right"

        }

        if (new.length > 5) return null
        return new.toInt().toString()

    }


    private fun updateOdds(){
        if (_games.isEmpty()){
            _couponCollapsed.value = true
            _couponHidden.value = true
            _couponState.value = _couponState.value.copy(oddValue = 0.0)
        }
        else {
            couponHidden.value = false
            var odd = 1.0
            _games.onEach {
                odd *= it.odds[it.selected.value!!].odd
            }
            odd = (odd * 100).roundToInt() / 100.0
            _couponState.value = _couponState.value.copy(oddValue = odd)
        }
    }

}