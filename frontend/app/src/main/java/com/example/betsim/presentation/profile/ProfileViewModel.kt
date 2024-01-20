package com.example.betsim.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.betsim.data.local.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
): ViewModel() {

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.LogoutClicked -> {
                sessionManager.clearSession()
            }
            ProfileEvent.ResetClicked -> {}
            ProfileEvent.DeleteClicked -> {}
        }
    }

}