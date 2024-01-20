package com.example.betsim.data.remote.responses

data class LoginResponse(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val tokenType: String
)