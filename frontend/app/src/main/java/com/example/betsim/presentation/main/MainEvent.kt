package com.example.betsim.presentation.main

import com.example.betsim.domain.model.TournamentGame

sealed class MainEvent {
    data class EnteredValue(val value: String): MainEvent()
    data class MakeBet(val value: String): MainEvent()
    data class AddGame(val game: TournamentGame): MainEvent()
    data class DeleteGame(val game: TournamentGame): MainEvent()
    data class CollapsedChange(val collapsed: Boolean): MainEvent()
    data class HiddenChange(val hidden: Boolean): MainEvent()
    data class DestinationChange(val destination: String?): MainEvent()

}