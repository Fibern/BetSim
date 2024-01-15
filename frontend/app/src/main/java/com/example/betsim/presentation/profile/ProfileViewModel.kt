package com.example.betsim.presentation.profile

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(

): ViewModel() {

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.LogoutClicked -> {}
            ProfileEvent.ResetClicked -> {}
        }
    }

}