package com.example.betsim.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(

) : ViewModel(){

    private val _login = mutableStateOf("")
    val login: State<String> = _login

    private val _password = mutableStateOf("")
    val password: State<String> = _password



    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EnteredLogin -> {
                _login.value = event.value
            }
            is AuthEvent.EnteredPassword -> {
                _password.value = event.value
            }
            else -> {}
        }
    }

}