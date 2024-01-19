package com.example.betsim.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.betsim.data.local.SecurePreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val helper: SecurePreferencesHelper
): ViewModel() {

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.LogoutClicked -> {
                helper.deleteLoginResponse()
            }
            ProfileEvent.ResetClicked -> {}
            ProfileEvent.DeleteClicked -> {}
        }
    }

}