package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Bet(
    @SerializedName("predictedWinner")
    val prediction: Odd,
    val score: String = "",
    val status: Int,
    val title: String = ""
)