package com.example.betsim.presentation.main

import com.example.betsim.data.remote.responses.Offer

sealed class MainEvent {
    data class EnteredValue(val value: String): MainEvent()
    data object MakeBet: MainEvent()
    data class AddGame(val offer: Offer): MainEvent()
    data class DeleteGame(val offer: Offer): MainEvent()
    data class CollapsedChange(val collapsed: Boolean): MainEvent()
    data class HiddenChange(val hidden: Boolean): MainEvent()
    data class DestinationChange(val destination: String?): MainEvent()

    data object Refresh: MainEvent()
}