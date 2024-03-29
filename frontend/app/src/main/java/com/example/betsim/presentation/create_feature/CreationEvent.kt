package com.example.betsim.presentation.create_feature

import com.example.betsim.data.model.OfferType
import com.example.betsim.data.model.EventIcons
import com.example.betsim.presentation.create_feature.create_offer.OddState
import java.time.LocalDate
import java.time.LocalTime

sealed class CreationEvent {
    data class EnteredName(val value: String): CreationEvent()
    data class SelectDropdown(val icon: EventIcons? = null, val offerType: OfferType? = null): CreationEvent()
    data object CreateClick : CreationEvent()
    data class EnteredDate(val date: LocalDate): CreationEvent()
    data class EnteredTime(val time: LocalTime): CreationEvent()
    data class EnteredTeamName(val name: String, val id: Int): CreationEvent()
    data class EnteredWinChance(val odd: String, val id: Int): CreationEvent()
    data object AddTeam: CreationEvent()
    data class RemoveTeam(val team: OddState): CreationEvent()
    data object CheckBoxChange: CreationEvent()
    data object NavigateFurtherClick: CreationEvent()
}