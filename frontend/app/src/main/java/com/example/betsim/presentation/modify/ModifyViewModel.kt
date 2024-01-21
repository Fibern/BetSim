package com.example.betsim.presentation.modify

import androidx.compose.runtime.IntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.OfferHolder
import com.example.betsim.data.local.SessionManager
import com.example.betsim.data.model.OfferType
import com.example.betsim.data.remote.responses.Offer
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateDoubleInput
import com.example.betsim.presentation.common.util.validateTextFieldState
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(
    offerHolder: OfferHolder,
    savedStateHandle: SavedStateHandle,
    private val repository: BetSimRepository,
    private val sessionManager: SessionManager
): ViewModel() {

    private val _offer = mutableStateOf(offerHolder.getOffer())
    val offer: State<Offer> = _offer

    private val _home = mutableStateOf(TextFieldState(""))
    val home: State<TextFieldState<String>> = _home

    private val _away = mutableStateOf(TextFieldState(""))
    val away: State<TextFieldState<String>> = _away

    private val _selected = mutableStateOf(TextFieldState<Int?>(null))
    val selected: State<TextFieldState<Int?>> = _selected

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _success = mutableStateOf(false)
    val success: State<Boolean> = _success

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val _eventId = mutableIntStateOf(-1)
    val eventId: IntState = _eventId

    init {
        _eventId.intValue = checkNotNull(savedStateHandle["id"])
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
                if (checkInput()) setScore()
            }
        }
    }

    private fun setScore() {
        viewModelScope.launch {
            _isLoading.value = true
            val score: String
            val winner: Int
            when (OfferType.entries[_offer.value.type]) {
                OfferType.Match -> {
                    score = "${_home.value.value} + ${_away.value.value}"
                    val h = _home.value.value.replace(',', '.').toDouble()
                    val a = _away.value.value.replace(',', '.').toDouble()
                    winner = if (a > h)
                        _offer.value.odds[0].id
                    else if (a < h)
                        _offer.value.odds[_offer.value.odds.size - 1].id
                    else if (_offer.value.odds.size == 3)
                        _offer.value.odds[1].id
                    else
                        0
                }

                OfferType.Selection -> {
                    score = _offer.value.odds[_selected.value.value!!].playerName
                    winner = _offer.value.odds[_selected.value.value!!].id
                }
            }
            val token = sessionManager.getCurrent()?.accessToken
            if (token != null) {
                val response = repository.patchOffer(token, _offer.value.id, winner, score)
                when (response) {
                    true -> {
                        _toastMessage.value = "Dodano wynik"
                        _success.value = true
                    }

                    false -> {
                        _toastMessage.value = "Coś poszło nie tak"
                        _isLoading.value = false
                    }
                }
            } else {
                _toastMessage.value = "Coś poszło nie tak"
                _isLoading.value = false
            }
        }
    }

    private fun checkInput(): Boolean {
        val type = OfferType.entries[_offer.value.type]
        return when(type){
            OfferType.Match -> {
                _home.value = validateTextFieldState(_home.value)
                _away.value = validateTextFieldState(_away.value)

                if(!_home.value.isError && !_away.value.isError){
                    if (_offer.value.odds.size == 2 && _home.value.value.replace(',','.').toDouble() == _away.value.value.replace(',','.').toDouble()){
                        _home.value = _home.value.copy(isError = true, errorText = "Wartości nie mogą być równe")
                        _away.value = _away.value.copy(isError = true, errorText = "Wartości nie mogą być równe")
                    }
                }

                !_home.value.isError && !_away.value.isError
            }
            OfferType.Selection -> {
                _selected.value = validateTextFieldState(_selected.value)
                !_selected.value.isError
            }
        }

    }

    fun clearToast(){
        _toastMessage.value = ""
    }

}