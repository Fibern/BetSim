package com.example.betsim.domain.response

import com.example.betsim.data.remote.LoginResponse

sealed class LoginStatus {
    data class Success(val loginResponse: LoginResponse): LoginStatus()
    data object Failure: LoginStatus()
    data object BadInternet: LoginStatus()
}