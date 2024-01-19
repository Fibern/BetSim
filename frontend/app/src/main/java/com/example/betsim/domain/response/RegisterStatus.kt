package com.example.betsim.domain.response

sealed class RegisterStatus {
    data object Success: RegisterStatus()
    data object BadInternet: RegisterStatus()
    data object DuplicateEmail: RegisterStatus()
    data object DuplicateUsername: RegisterStatus()
    data object DuplicateEmailAndUsername: RegisterStatus()
    data object Unknown: RegisterStatus()
}