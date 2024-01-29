package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class RankingResponse(
    @SerializedName("message")
    val user: RankingUsers,
    val succes: Boolean,
    val errors: Errors
)