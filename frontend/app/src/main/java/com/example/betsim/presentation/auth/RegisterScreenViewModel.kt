package com.example.betsim.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateTextFieldState
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val repository: BetSimRepository
) : ViewModel(){
    private val _email = mutableStateOf(TextFieldState(""))
    val email: State<TextFieldState<String>> = _email

    private val _login = mutableStateOf(TextFieldState(""))
    val login: State<TextFieldState<String>> = _login


    private val _password = mutableStateOf(TextFieldState(""))
    val password: State<TextFieldState<String>> = _password

    private val _password2 = mutableStateOf(TextFieldState(""))
    val password2: State<TextFieldState<String>> = _password2

    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EnteredLogin -> {
                _login.value = _login.value.copy(value = event.value)
            }
            is AuthEvent.EnteredPassword -> {
                _password.value = _password.value.copy(value = event.value)
            }
            is AuthEvent.EnteredEmail -> {
                _email.value = _email.value.copy(value = event.value)
            }
            is AuthEvent.EnteredPassword2 -> {
                _password2.value = _password2.value.copy(value = event.value)
            }
            AuthEvent.OnAuthClick -> {
                if (!checkInputFields()) return
                if (!validateRegister()) return
                onRegister()
            }
        }
    }

    private fun checkInputFields(): Boolean{
        _login.value = validateTextFieldState(_login.value)
        _email.value = validateTextFieldState(_email.value)
        _password.value = validateTextFieldState(_password.value)
        _password2.value = validateTextFieldState(_password2.value)

        return !_login.value.isError || !_email.value.isError || !_password.value.isError || !_password2.value.isError
    }

    private fun validateRegister(): Boolean{
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        if (!emailRegex.matches(_email.value.value)){
            _email.value = _email.value.copy(isError = true, errorText = "Niepoprawny adres email")
        }
        if (_password.value.value != _password2.value.value){
            _password.value = _password.value.copy(isError = true, errorText = "Podane hasła muszą się zgadzać")
            _password2.value = _password2.value.copy(isError = true, errorText = "Podane hasła muszą się zgadzać")
        }
        return !_email.value.isError || !_password.value.isError
    }

    private fun onRegister(){
        viewModelScope.launch {
            val result = repository.register("test@test.pl","test@test.pl","testa1")
        }
    }

}