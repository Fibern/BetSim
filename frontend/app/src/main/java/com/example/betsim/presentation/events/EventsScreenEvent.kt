package com.example.betsim.presentation.events

sealed class EventsScreenEvent {
    data class DeleteClicked(val id: Int): EventsScreenEvent()
    data object Refresh: EventsScreenEvent()
}