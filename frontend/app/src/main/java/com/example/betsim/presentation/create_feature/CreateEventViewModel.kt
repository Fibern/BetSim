package com.example.betsim.presentation.create_feature

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CreateEventViewModel @Inject constructor(

): ViewModel() {

    private val _eventName = mutableStateOf("")
    val eventName = _eventName

    fun onEvent(event: CreationEvent){

        when(event){
            is CreationEvent.EnteredName -> {
                _eventName.value = event.value
            }
        }

    }

}