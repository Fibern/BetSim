package com.example.betsim.presentation.profile

sealed class ProfileEvent {
    data object LogoutClicked: ProfileEvent()
    data object DeleteClicked: ProfileEvent()
}