package com.example.betsim.presentation.modify

sealed class ModifyEvent {
    data class ScoreEntered(val home: Boolean, val score: String): ModifyEvent()
    data class OptionChanged(val id: Int): ModifyEvent()
    data object ConfirmClick: ModifyEvent()
}