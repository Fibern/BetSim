package com.example.betsim.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: BetSimRepository
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
            AuthEvent.OnAuthClick -> {
                login()
            }
            else -> {}
        }
    }

    private fun login(){
        viewModelScope.launch {
            val login = repository.login("test@test.pl", "testa1")
            val logina = repository.login("test@test.pl", "testa1a")
        }
    }

}