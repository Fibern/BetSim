package com.example.betsim.data.remote.status

sealed class RegisterStatus {
    data object Success: RegisterStatus()
    data object BadInternet: RegisterStatus()
    data object DuplicateEmail: RegisterStatus()
    data object DuplicateUsername: RegisterStatus()
    data object DuplicateEmailAndUsername: RegisterStatus()
    data object Unknown: RegisterStatus()
}