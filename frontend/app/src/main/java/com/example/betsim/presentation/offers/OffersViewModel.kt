package com.example.betsim.presentation.offers

import androidx.compose.runtime.IntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.OfferHolder
import com.example.betsim.data.remote.responses.Offer
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.presentation.util.Screen
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: BetSimRepository,
    private val offerHolder: OfferHolder
): ViewModel() {

    private val _offers = mutableStateListOf<Offer>()
    val offers: List<Offer> = _offers

    private val _isToday = mutableStateOf(false)

    private val _isMod = mutableStateOf(false)
    val isMod: State<Boolean> = _isMod

    private val _id = mutableIntStateOf(-1)
    val id: IntState = _id

    private val _route = mutableStateOf("")
    val route: State<String> = _route


    init {
        _isToday.value = checkNotNull(savedStateHandle["today"])
        _isMod.value = checkNotNull(savedStateHandle["mod"])
        _id.intValue = checkNotNull(savedStateHandle["id"])
        _route.value = "${Screen.AddGameMainDefaultScreen.route}?id=${_id.intValue}"
        getGames()
    }

    fun onEvent(event: OffersEvent){
        when(event){
            is OffersEvent.LoadList -> {
                //TODO()
                // _games.removeAt(event.index)
               // _games.add(event.index, event.game)
            }
            is OffersEvent.OnSelect -> {
                event.offer.selected = event.index
            }
            is OffersEvent.ModifyClicked -> {
                offerHolder.saveOffer(_offers[event.index])
            }
        }
    }


    private fun getGames() {

        viewModelScope.launch {
            if (_id.intValue != -1){
                val response = repository.getOffer(_id.intValue)
                when(response){
                    BasicStatus.BadInternet -> {}
                    BasicStatus.Failure -> {}
                    is BasicStatus.Success -> {
                        val tmp = response.response.offer.toMutableList()
                        _offers.clear()
                        if (_isToday.value)
                            _offers.addAll(tmp.filter { it.dateTime.toLocalDate() == LocalDate.now() })
                        else
                            _offers.addAll(tmp)
                    }
                }
            }
        }
    }
}