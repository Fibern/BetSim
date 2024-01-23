package com.example.betsim.presentation.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.SecurePreferencesHelper
import com.example.betsim.data.local.SessionManager
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val repository: BetSimRepository,
    private val helper: SecurePreferencesHelper
): ViewModel() {

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val _success = mutableStateOf(false)
    val success: State<Boolean> =_success

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.LogoutClicked -> {
                onLogout()
            }
            ProfileEvent.ResetClicked -> {}
            ProfileEvent.DeleteClicked -> {
                onDelete()
            }
        }
    }

    private fun onDelete(){
        viewModelScope.launch {
            _isLoading.value = true
            val login = sessionManager.getCurrent()
            if (login != null){
                val response = repository.deleteUser(login.accessToken)
                if (response){
                    _toastMessage.value = "Konto zostało usunięte"
                    onLogout()
                }else{
                    _toastMessage.value = "Coś poszło nie tak"
                }
            }else{
                _toastMessage.value = "Coś poszło nie tak"
            }
            _isLoading.value = false
        }
    }

    private fun onLogout(){
        viewModelScope.launch {
            val token = sessionManager.getCurrent()
            if (token != null) {
                val response = repository.deleteDeviceToken(
                    token.accessToken,
                    helper.getDeviceToken()
                )
                Log.i("kd",response.toString())
            }
            sessionManager.clearSession()
            _success.value = true
        }
    }

    fun clearToast(){
        _toastMessage.value = ""
    }

}