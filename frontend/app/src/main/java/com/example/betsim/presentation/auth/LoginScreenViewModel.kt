package com.example.betsim.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateTextFieldState
import com.example.betsim.presentation.common.util.validateTextInput
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: BetSimRepository
) : ViewModel(){

    private val _login = mutableStateOf(TextFieldState(""))
    val login: State<TextFieldState<String>> = _login

    private val _password = mutableStateOf(TextFieldState(""))
    val password: State<TextFieldState<String>> = _password

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val _success = mutableStateOf(false)
    val success: State<Boolean> =_success

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading


    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EnteredLogin -> {
                if (!validateTextInput(event.value)) return
                _login.value = _login.value.copy(value = event.value)
            }
            is AuthEvent.EnteredPassword -> {
                if (!validateTextInput(event.value)) return
                _password.value = _password.value.copy(value = event.value)
            }
            AuthEvent.OnAuthClick -> {
                if (!checkInputFields()) return
                onLogin()
            }
            else -> {}
        }
    }


    private fun checkInputFields(): Boolean{
        _login.value = validateTextFieldState(_login.value)
        _password.value = validateTextFieldState(_password.value)
        return !(_login.value.isError || _password.value.isError)
    }

    private fun onLogin(){
        viewModelScope.launch {
            val login = repository.login("test@test.pl", "testa1")
            val logina = repository.login("test@test.pl", "testa1a")
        }
    }

    fun clearToast(){
        _toastMessage.value = ""
    }

}