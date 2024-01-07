package com.example.betsim.presentation.create_feature

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CreateEventViewModel @Inject constructor(

): ViewModel() {

    private val _eventState = mutableStateOf(EventState())
    val eventState = _eventState

    fun onEvent(event: CreationEvent){

        when(event){
            is CreationEvent.EnteredName -> {
                _eventState.value = _eventState.value.copy(name =  event.value)
            }

            is CreationEvent.SelectDropdown -> {
                _eventState.value = _eventState.value.copy(icon = event.icon)
            }

            CreationEvent.CreateClick -> {
                //TODO()
            }

            else -> {}
        }

    }

}