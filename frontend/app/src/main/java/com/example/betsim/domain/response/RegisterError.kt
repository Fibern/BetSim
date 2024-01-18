package com.example.betsim.domain.response

sealed class RegisterError {
    data object Success: RegisterError()
    data object BadInternet: RegisterError()
    data object DuplicateEmail: RegisterError()
    data object DuplicateUsername: RegisterError()
    data object DuplicateEmailAndUsername: RegisterError()
    data object Unknown: RegisterError()
}