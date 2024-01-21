package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class OfferResponse(
    @SerializedName("message")
    val offer: List<Offer>,
    val succes: Boolean,
    val errors: Errors
)