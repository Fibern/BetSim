package com.example.betsim.data.remote

data class LoginResponse(
    val accessToken: String,
    val expiresIn: Int,
    val refreshToken: String,
    val tokenType: String
)