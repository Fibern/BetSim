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
import java.time.LocalDateTime
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

    private val _isLoadingSemiTransparent = mutableStateOf(false)
    val isLoadingSemiTransparent: State<Boolean> = _isLoadingSemiTransparent

    private val _user = mutableStateOf(User("","",false,0.0))
    val user: State<User> = _user

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage
    init {
        getUser()
    }


    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.EnteredValue -> {
                val value = validateDoubleInput(event.value, min(10000.0, _user.value.points!!)) ?: return
                var winningsDouble = value.replace(',','.').toDouble() * _couponState.value.oddValue
                winningsDouble = min(winningsDouble, 1000000.0)
                val winnings = "%,2f".format(winningsDouble).trimEnd('0').trimEnd(',')
                val textField = _couponState.value.betValue.copy(value = value)
                _couponState.value = _couponState.value.copy(betValue = textField, winnings = winnings)
                setMessage(winningsDouble)
            }
            MainEvent.MakeBet -> {
                val value = _couponState.value.betValue.value.replace(',','.').toDoubleOrNull() ?: return
                if (value < 2.0) return
                makeBet(value)
            }
            is MainEvent.DeleteGame -> {
                val tmp = _couponState.value.offers - event.offer
                _couponState.value = _couponState.value.copy(
                    offers = tmp
                )
                event.offer.selected = null
                updateOdds()
            }
            is MainEvent.AddGame -> {
                if (event.offer in _couponState.value.offers) return
                val tmp = _couponState.value.offers + event.offer
                _couponState.value = _couponState.value.copy(
                    offers = tmp
                )
                updateOdds()
            }
            is MainEvent.CollapsedChange -> {
                _couponState.value = _couponState.value.copy(
                    collapsed = event.collapsed
                )
            }
            is MainEvent.HiddenChange -> {
                val hidden = if (_couponState.value.offers.isNotEmpty()){
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
                    Screen.CreateOfferMainScreen.route -> {
                        true
                    }
                    Screen.CreateEventScreen.route -> {
                        true
                    }
                    else -> {
                        false
                    }
                }

            }

            MainEvent.Refresh -> getUser()
        }
    }

    private fun getUser() {
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


    private fun updateOdds(){
        if (_couponState.value.offers.isEmpty()){
            _couponState.value = _couponState.value.copy(
                oddValue = 0.0,
                collapsed = true,
                hidden = true
            )
        }
        else {
            var odd = 1.0
            _couponState.value.offers.onEach {
                odd *= it.odds[it.selected!!].oddValue
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

    private fun makeBet(value: Double) {
        viewModelScope.launch {
            _isLoadingSemiTransparent.value = true
            val bets: MutableList<HashMap<String, Int>> = mutableListOf()
            _couponState.value.offers.forEach {
                bets.add(
                    hashMapOf(
                        "offertId" to it.id,
                        "predictedWinnerId" to it.odds[it.selected!!].id
                    )
                )
            }
            val login = sessionManager.getCurrent()
            if (login != null) {
                val response = repository.postCoupon(
                    login.accessToken, bets, value, _couponState.value.oddValue, LocalDateTime.now().minusHours(1).toString())
                if (response){
                    clearOffers()
                    _user.value = _user.value.copy(points = _user.value.points!! - value)
                    _isLoadingSemiTransparent.value = false
                    _toastMessage.value = "Postawiono zakład"
                }else{
                    _isLoadingSemiTransparent.value = false
                    _toastMessage.value = "Coś poszło nie tak"

                }
            }else{
                _isLoadingSemiTransparent.value = false
                _toastMessage.value = "Coś poszło nie tak"

            }
        }
    }

    private fun clearOffers(){
        while (_couponState.value.offers.isNotEmpty()){
            onEvent(MainEvent.DeleteGame(_couponState.value.offers[0]))
        }
    }

    fun clearToast(){
        _toastMessage.value = ""
    }

}