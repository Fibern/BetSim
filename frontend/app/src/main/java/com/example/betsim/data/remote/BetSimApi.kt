package com.example.betsim.data.remote

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface BetSimApi {

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("register")
    suspend fun register(@Body requestBody: RequestBody) : JsonObject

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("login")
    suspend fun login(@Body requestBody: RequestBody) : JsonObject

}