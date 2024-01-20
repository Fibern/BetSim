package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userName")
    val username: String,
    val email: String,
    @SerializedName("isOddsMaker")
    val isMod: Boolean,
    val points: Int?
)
