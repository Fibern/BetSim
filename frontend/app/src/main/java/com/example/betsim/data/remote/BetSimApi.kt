package com.example.betsim.data.remote

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BetSimApi {

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("Account/register")
    suspend fun register(@Body requestBody: RequestBody) : JsonObject

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("Account/login")
    suspend fun login(@Body requestBody: RequestBody) : JsonObject

}