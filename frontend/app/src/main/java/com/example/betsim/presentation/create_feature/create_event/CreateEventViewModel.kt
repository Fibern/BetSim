package com.example.betsim.presentation.create_feature.create_event

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.SessionManager
import com.example.betsim.domain.model.EventIcons
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateTextFieldState
import com.example.betsim.presentation.create_feature.CreationEvent
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val repository: BetSimRepository
): ViewModel() {

    private val _icon = mutableStateOf(TextFieldState<EventIcons?>(value = null))
    val icon: State<TextFieldState<EventIcons?>> = _icon

    private val _name = mutableStateOf(TextFieldState(value = ""))
    val name: State<TextFieldState<String>> = _name

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _success = mutableStateOf(false)
    val success: State<Boolean> = _success

    fun onEvent(event: CreationEvent){

        when(event){
            is CreationEvent.EnteredName -> {
                _name.value = _name.value.copy(value =  event.value)
            }

            is CreationEvent.SelectDropdown -> {
                _icon.value = _icon.value.copy(value = event.icon)
            }

            CreationEvent.CreateClick -> {
                if (checkInput()){
                    onCreate()
                }
            }
            else -> {}
        }

    }

    private fun checkInput(): Boolean {
        _name.value = validateTextFieldState(_name.value)
        _icon.value = validateTextFieldState(_icon.value)

         return !_name.value.isError && !_icon.value.isError
    }

    private fun onCreate(){
        viewModelScope.launch {
            _isLoading.value = true
            val token = sessionManager.getCurrent()?.accessToken
            if (token != null) {
                val response = repository.postEvent(
                    token,
                    _name.value.value,
                    _icon.value.value!!.name
                )
                when(response){
                    true -> {
                        _toastMessage.value = "Utworzono wydarzenie"
                        _success.value = true
                    }
                    false -> {
                        _toastMessage.value = "Coś poszło nie tak"
                        _isLoading.value = false
                    }
                }
            }else{
                _toastMessage.value = "Coś poszło nie tak"
                _isLoading.value = false
            }
        }
    }

    fun clearToast(){
        _toastMessage.value = ""
    }

}