package com.example.betsim.data.remote

import com.example.betsim.data.remote.responses.LoginResponse
import com.example.betsim.data.remote.responses.UserResponse
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface BetSimApi {

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("Account/register")
    suspend fun register(@Body requestBody: RequestBody): Response<JsonObject>

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("/login")
    suspend fun login(@Body requestBody: RequestBody): Response<LoginResponse?>

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/User")
    suspend fun getUser(@Header("authorization") authorization: String): Response<UserResponse?>

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("/refresh")
    suspend fun refresh(@Body requestBody: RequestBody): Response<LoginResponse?>

}