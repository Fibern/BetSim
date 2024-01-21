package com.example.betsim.presentation.tournament_details

import com.example.betsim.data.remote.responses.Offer

sealed class TournamentDetailsEvent {
    data class OnSelect(val offer: Offer, val index: Int): TournamentDetailsEvent()
    data class LoadList(val offer: Offer, val index: Int): TournamentDetailsEvent()
}