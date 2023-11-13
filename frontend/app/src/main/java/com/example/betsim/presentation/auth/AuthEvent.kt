package com.example.betsim.presentation.auth

sealed class AuthEvent{
    data class EnteredLogin(val value:String): AuthEvent()
    data class EnteredPassword(val value:String): AuthEvent()
}
