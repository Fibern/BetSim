package com.example.betsim.presentation.user_main

import com.example.betsim.domain.model.TournamentGame

sealed class UserMainEvent {
    data class EnteredValue(val value: String): UserMainEvent()
    data class MakeBet(val value: String): UserMainEvent()
    data class AddGame(val game: TournamentGame): UserMainEvent()
    data class DeleteGame(val game: TournamentGame): UserMainEvent()
    data class CollapsedChange(val collapsed: Boolean): UserMainEvent()
    data class HiddenChange(val hidden: Boolean): UserMainEvent()

}