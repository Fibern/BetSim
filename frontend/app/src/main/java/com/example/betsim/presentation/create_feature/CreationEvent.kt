package com.example.betsim.presentation.create_feature

import com.example.betsim.presentation.common.data.EventIcons

sealed class CreationEvent {
    data class EnteredName(val value: String): CreationEvent()
    data class SelectDropdown(val icon: EventIcons? = null, val text: String? = null): CreationEvent()
    data object CreateClick : CreationEvent()
}