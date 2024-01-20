package com.example.betsim.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.SessionManager
import com.example.betsim.data.remote.responses.User
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.presentation.common.util.validateDoubleInput
import com.example.betsim.presentation.util.Screen
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.min
import kotlin.math.roundToInt

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val repository: BetSimRepository
) : ViewModel() {

    private val _mainAppBarsHidden = mutableStateOf(false)
    val mainAppBarsHidden: State<Boolean> = _mainAppBarsHidden

    private val _couponState = mutableStateOf(MainCouponState())
    val couponState: State<MainCouponState> = _couponState

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _user = mutableStateOf(User("","",false,0))
    val user: State<User> = _user

    init {
        viewModelScope.launch {
            val login = sessionManager.getCurrent()
            if (login == null){
                _isLoading.value = false
                return@launch
            }
            when (val response: BasicStatus<User> = repository.getUser(login.accessToken)) {
                BasicStatus.BadInternet -> {}
                BasicStatus.Failure -> {}
                is BasicStatus.Success -> {
                    _user.value = response.response
                }
            }
            _isLoading.value = false
        }
    }

    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.EnteredValue -> {
                val value = validateDoubleInput(event.value, 10000.0) ?: return
                var winningsDouble = value.replace(',','.').toDouble() * _couponState.value.oddValue
                winningsDouble = min(winningsDouble, 1000000.0)
                val winnings = "%,2f".format(winningsDouble).trimEnd('0').trimEnd(',')
                val textField = _couponState.value.betValue.copy(value = value)
                _couponState.value = _couponState.value.copy(betValue = textField, winnings = winnings)
                setMessage(winningsDouble)
            }
            MainEvent.MakeBet -> {
                // TODO()
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

    private fun setMessage(winnings: Double){
        val textField = _couponState.value.betValue
        val value = textField.value.replace(',','.').toDouble()
        val message = if (winnings > 1000000.0)
                          "Maksymalna wygrana wynosi 1000000"
                      else if (value == 10000.0)
                          "Maksymalna stawka wynosi 10000"
                      else if (value < 2)
                          "Minimalna stawaka wynosi 2"
                      else
                          ""
        _couponState.value = _couponState.value.copy(
            betValue = textField.copy(errorText = message)
        )
    }

}