package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class User(
    val email: String,
    @SerializedName("isOddMaker")
    val isMod: Boolean,
    val points: Int?
)
