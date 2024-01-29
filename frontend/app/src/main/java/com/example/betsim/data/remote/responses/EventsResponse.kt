package com.example.betsim.data.remote.responses

import com.google.gson.annotations.SerializedName

data class EventsResponse(
    val errors: Errors,
    @SerializedName("message")
    val events: List<Event>,
    val succes: Boolean
)