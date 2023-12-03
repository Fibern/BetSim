package com.example.betsim.presentation.tournament_details_user

import com.example.betsim.domain.model.TournamentGame

sealed class TournamentDetailsEvent {
    data class OnSelect(val game: TournamentGame, val index: Int): TournamentDetailsEvent()
    data class LoadList(val game: TournamentGame, val index: Int): TournamentDetailsEvent()
}