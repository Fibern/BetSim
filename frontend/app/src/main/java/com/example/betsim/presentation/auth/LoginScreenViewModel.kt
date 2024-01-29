package com.example.betsim.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.SecurePreferencesHelper
import com.example.betsim.data.local.SessionManager
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.presentation.common.util.TextFieldState
import com.example.betsim.presentation.common.util.validateTextFieldState
import com.example.betsim.presentation.common.util.validateTextInput
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: BetSimRepository,
    private val sessionManager: SessionManager,
    private val helper: SecurePreferencesHelper
) : ViewModel(){

    private val _login = mutableStateOf(TextFieldState("Admin"))
    val login: State<TextFieldState<String>> = _login

    private val _password = mutableStateOf(TextFieldState("zaq12wsx"))
    val password: State<TextFieldState<String>> = _password

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val _success = mutableStateOf(false)
    val success: State<Boolean> =_success

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _isLoading.value = true
            val login = sessionManager.getCurrent()
            if (login != null) {
                _success.value = true
            }else{
                _isLoading.value = false
            }
        }
    }


    fun onEvent(event: AuthEvent){
        when(event){
            is AuthEvent.EnteredLogin -> {
                if (!validateTextInput(event.value)) return
                _login.value = _login.value.copy(value = event.value.trim())
            }
            is AuthEvent.EnteredPassword -> {
                if (!validateTextInput(event.value)) return
                _password.value = _password.value.copy(value = event.value.trim())
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
            _isLoading.value = true
            when(val result = repository.login(_login.value.value, _password.value.value)){
                BasicStatus.BadInternet -> {
                    _toastMessage.value = "Brak połączenia z internetem!"
                    _isLoading.value = false
                }
                BasicStatus.Failure -> {
                    _toastMessage.value = "Błędny login lub hasło!"
                    _isLoading.value = false
                }
                is BasicStatus.Success -> {
                    repository.addDeviceToken(result.response.accessToken, helper.getDeviceToken())
                    sessionManager.saveLoginResponse(result.response)
                    _success.value = true
                }
            }
        }
    }



    fun clearToast(){
        _toastMessage.value = ""
    }

}