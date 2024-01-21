package com.example.betsim.presentation.offers

import com.example.betsim.data.remote.responses.Offer

sealed class OffersEvent {
    data class OnSelect(val offer: Offer, val index: Int): OffersEvent()
    data class LoadList(val offer: Offer, val index: Int): OffersEvent()
    data class ModifyClicked(val index: Int): OffersEvent()
}