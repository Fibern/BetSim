package com.example.betsim.presentation.create_feature

sealed class CreationEvent {
    data class EnteredName(val value: String): CreationEvent()
}