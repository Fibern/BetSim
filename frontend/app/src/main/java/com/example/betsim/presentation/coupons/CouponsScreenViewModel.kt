package com.example.betsim.presentation.coupons

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.CouponHolder
import com.example.betsim.data.local.SessionManager
import com.example.betsim.data.model.Category
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CouponsScreenViewModel @Inject constructor(
    private val repository: BetSimRepository,
    private val sessionManager: SessionManager,
    private val couponHolder: CouponHolder
): ViewModel(){

    private val _inGame = mutableStateListOf<Category>()
    val inGame: List<Category> = _inGame

    private val _finished = mutableStateListOf<Category>()
    val finished: List<Category> = _finished

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading


    init {
        getCoupons()
    }

    fun onEvent(event: CouponsEvent){
        when(event){
            is CouponsEvent.ItemClicked -> {
                couponHolder.saveCoupon(event.coupon)
            }

            CouponsEvent.Refresh -> getCoupons()
        }
    }

    private fun getCoupons() {

        viewModelScope.launch {
            _isLoading.value = true
            val login = sessionManager.getCurrent()
            if (login != null) {
                when (val response = repository.getCoupons(login.accessToken)) {
                    BasicStatus.BadInternet -> {
                        //TODO()
                    }

                    BasicStatus.Failure -> {
                        //TODO()
                    }

                    is BasicStatus.Success -> {

                        val (inGameResponse, finishedResponse) = response.response.coupons.partition { it.status == 1 }

                        val inGameTmp = inGameResponse
                            .sortedByDescending { it.dateTime }
                            .groupBy { it.dateTime.toLocalDate() }
                            .toMap()


                        val finishedTmp = finishedResponse
                            .sortedByDescending { it.dateTime }
                            .groupBy { it.dateTime.toLocalDate() }
                            .toMap()

                        _inGame.clear()
                        _finished.clear()

                        _inGame.addAll(
                            inGameTmp.map {
                                Category(
                                    header = it.key,
                                    coupons = it.value
                                )
                            }
                        )

                        _finished.addAll(
                            finishedTmp.map {
                                Category(
                                    header = it.key,
                                    coupons = it.value
                                )
                            }
                        )
                    }
                }
            }
            _isLoading.value = false
        }
    }
}