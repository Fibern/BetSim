package com.example.betsim.presentation.create_feature

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.OfferType
import java.time.LocalDate
import java.time.LocalTime

data class CreateGameState (
    val type: OfferType = OfferType.Match,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val drawable: Boolean = false,
    val odds: SnapshotStateList<Odd> =  mutableStateListOf(Odd(0, "", "0"),Odd(0, "", "0")),
    val remaining: Double = 100.0
)