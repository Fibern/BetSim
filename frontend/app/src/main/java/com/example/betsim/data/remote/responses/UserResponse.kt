package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val errors: Errors,
    @SerializedName("message")
    val user: User,
    @SerializedName("succes")
    val success: Boolean
)