package com.example.betsim.presentation.coupons

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.Coupon
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

data class Category(
    val header: LocalDate,
    val coupons: List<Coupon>
)
class CouponsScreenViewModel @Inject constructor(

): ViewModel(){

    private val _coupons = mutableStateOf(listOf<Category>())
    val coupons = _coupons


    init {

        getCoupons()

    }

    private fun getCoupons() {
        val odd1 = Odd(1, "tmp1", 1.3)
        val odd2 = Odd(1, "tmp2", 1.7)
        val odd3 = Odd(1, "remis", 1.8)
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
            selected = mutableStateOf(2)
        )
        val game5 = TournamentGame(
            5,
            "Polska",
            "Estonia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(1)
        )
        val game6 = TournamentGame(
            6,
            "Polska",
            "Estonia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(0)
        )
        val game7 = TournamentGame(
            7,
            "Polska",
            "Estonia",
            listOf(odd1, odd2, odd3),
            selected = mutableStateOf(1)
        )
        val game8 =
            TournamentGame(8, "Polska", "Łotwa", listOf(odd1, odd2), selected = mutableStateOf(0))
        val coupon1 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon2 = Coupon(
            1,
            listOf(game6, game7),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon3 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2002, 10, 10, 12, 0, 0)
        )
        val coupon4 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2003, 10, 10, 12, 0, 0)
        )
        val coupon5 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2020, 10, 10, 12, 0, 0)
        )
        val coupon6 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2021, 10, 10, 12, 0, 0)
        )
        val coupon7 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2021, 10, 10, 12, 0, 0)
        )
        val coupon8 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon9 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon10 = Coupon(
            1,
            listOf(game6, game7),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon11 = Coupon(
            1,
            listOf(game8),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon12 = Coupon(
            1,
            listOf(game8),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon13 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon14 = Coupon(
            1,
            listOf(game6, game7),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon15 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 10, 0, 0)
        )
        val coupon16 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 11, 0, 0)
        )
        val coupon17 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 10, 0, 0)
        )
        val coupon18 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 10, 0, 0)
        )
        val coupon19 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon20 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon21 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon22 = Coupon(
            1,
            listOf(game6, game7),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon23 = Coupon(
            1,
            listOf(game8),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon24 = Coupon(
            1,
            listOf(game8),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon25 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon26 = Coupon(
            1,
            listOf(game6, game7),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2023, 12, 12, 12, 0, 0)
        )
        val coupon27 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2000, 10, 10, 10, 0, 0)
        )
        val coupon28 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 18, 0, 0)
        )
        val coupon29 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 16, 0, 0)
        )
        val coupon30 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 14, 0, 0)
        )
        val coupon31 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            true,
            2.54,
            25.0,
            0.0,
            LocalDateTime.of(2000, 10, 10, 12, 0, 0)
        )
        val coupon32 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2024, 1, 1, 12, 0, 0)
        )
        val coupon33 = Coupon(
            1,
            listOf(game1, game2, game3, game4, game5),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2024, 1, 1,  12, 0, 0)
        )
        val coupon34 = Coupon(
            1,
            listOf(game6, game7),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2024, 1, 1, 12, 0, 0)
        )
        val coupon35 = Coupon(
            1,
            listOf(game8),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2024, 1, 1,  12, 0, 0)
        )
        val coupon36 = Coupon(
            1,
            listOf(game8),
            false,
            2.54,
            25.0,
            26.0,
            LocalDateTime.of(2024, 1, 1,  12, 0, 0)
        )

        val list = listOf(
            coupon1,
            coupon2,
            coupon3,
            coupon4,
            coupon5,
            coupon6,
            coupon7,
            coupon8,
            coupon9,
            coupon10,
            coupon11,
            coupon12,
            coupon13,
            coupon14,
            coupon15,
            coupon16,
            coupon17,
            coupon18,
            coupon19,
            coupon20,
            coupon21,
            coupon22,
            coupon23,
            coupon24,
            coupon25,
            coupon26,
            coupon27,
            coupon28,
            coupon29,
            coupon30,
            coupon31,
            coupon32,
            coupon33,
            coupon34,
            coupon35,
            coupon36
        ).sortedByDescending {
            it.date
        }.groupBy {
            it.date.toLocalDate()
        }.toMap()

        _coupons.value = list.map {
            Category(
                header = it.key,
                coupons = it.value
            )
        }
    }
}