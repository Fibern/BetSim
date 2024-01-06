package com.example.betsim.presentation.tournaments

import com.example.betsim.domain.model.Tournament

data class TournamentsState(
    val tournaments: List<Tournament> = emptyList()
)