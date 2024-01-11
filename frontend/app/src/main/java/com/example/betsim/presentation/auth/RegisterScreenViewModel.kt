package com.example.betsim.presentation.auth

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.repository.BetSimRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val repository: BetSimRepository
) : ViewModel(){
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _login = mutableStateOf("")
    val login: State<String> = _login


    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _password2 = mutableStateOf("")
    val password2: State<String> = _password2

    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EnteredLogin -> {
                _login.value = event.value
            }
            is AuthEvent.EnteredPassword -> {
                _password.value = event.value
            }
            is AuthEvent.EnteredEmail -> {
                _email.value = event.value
            }
            is AuthEvent.EnteredPassword2 -> {
                _password2.value = event.value
            }
            is AuthEvent.OnAuthClick -> {
                onRegister()
            }
        }
    }

    private fun onRegister(){
        viewModelScope.launch {
            val result = repository.register("test@test.pl","testa1")
        }
    }

}