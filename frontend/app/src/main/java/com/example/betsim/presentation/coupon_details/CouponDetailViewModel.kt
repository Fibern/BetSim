package com.example.betsim.presentation.coupon_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.Coupon
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame
import java.time.LocalDateTime
import javax.inject.Inject

class CouponDetailViewModel @Inject constructor(

): ViewModel(){

    private val _coupon = mutableStateOf(CouponDetailsState())
    val coupon : MutableState<CouponDetailsState> = _coupon


    init {
        getCoupon()
    }

    private fun getCoupon(){
        val odd1 = Odd(1, "tmp1", "1.3")
        val odd2 = Odd(1, "tmp2", "1.7")
        val odd3 = Odd(1, "remis", "1.8")
        val game1 = TournamentGame(
            1,
            "Polska",
            "Mołdawia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(0)
        )
        val game2 = TournamentGame(
            2,
            "Polska",
            "Estonia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(2)
        )
        val game3 = TournamentGame(
            3,
            "Polska",
            "Estonia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(1)
        )
        val game4 = TournamentGame(
            4,
            "Polska",
            "Estonia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(2),
            status = "lost"
        )
        val game5 = TournamentGame(
            5,
            "Polska",
            "Estonia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(1),
            status = "won"
        )
        val coupon1 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0),
        )
        _coupon.value = CouponDetailsState(coupon1, true)
    }

}