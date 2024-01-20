package com.example.betsim.presentation.create_feature.create_event

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.domain.model.EventIcons
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateTextFieldState
import com.example.betsim.presentation.create_feature.CreationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(

): ViewModel() {

    private val _icon = mutableStateOf(TextFieldState<EventIcons?>(value = null))
    val icon: State<TextFieldState<EventIcons?>> = _icon

    private val _name = mutableStateOf(TextFieldState(value = ""))
    val name: State<TextFieldState<String>> = _name


    fun onEvent(event: CreationEvent){

        when(event){
            is CreationEvent.EnteredName -> {
                _name.value = _name.value.copy(value =  event.value)
            }

            is CreationEvent.SelectDropdown -> {
                _icon.value = _icon.value.copy(value = event.icon)
            }

            CreationEvent.CreateClick -> {
                val correct = checkInput()
                //TODO()
            }

            else -> {}
        }

    }

    private fun checkInput(): Boolean {
        _name.value = validateTextFieldState(_name.value)
        _icon.value = validateTextFieldState(_icon.value)

         return !_name.value.isError && !_icon.value.isError
    }

}