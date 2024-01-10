package com.example.betsim.presentation.create_feature

import com.example.betsim.domain.model.EventIcons

data class EventState(
    val icon: EventIcons? = null,
    val name: String = ""
)
