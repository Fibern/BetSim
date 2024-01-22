package com.example.betsim.data.remote

import com.example.betsim.data.remote.responses.CouponsResponse
import com.example.betsim.data.remote.responses.EventsResponse
import com.example.betsim.data.remote.responses.LoginResponse
import com.example.betsim.data.remote.responses.OfferResponse
import com.example.betsim.data.remote.responses.RankingResponse
import com.example.betsim.data.remote.responses.UserResponse
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/Event")
    suspend fun getEvents(): Response<EventsResponse>

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/User/Event")
    suspend fun getEventByUser(@Header("authorization") authorization: String): Response<EventsResponse>

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("/Event")
    suspend fun postEvent(
        @Header("authorization") authorization: String,
        @Body requestBody: RequestBody
    ): Response<JsonObject>

    @Headers("accept: */*", "Content-Type: application/json")
    @DELETE("/Event/{id}")
    suspend fun deleteEvent(
        @Header("authorization") authorization: String,
        @Path("id") id: Int
    ): Response<JsonObject>


    @Headers("accept: */*", "Content-Type: application/json")
    @POST("/Offert/{eventId}")
    suspend fun postOffer(
        @Header("authorization") authorization: String,
        @Path("eventId") id: Int,
        @Body requestBody: RequestBody
    ): Response<JsonObject>

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/Offert/{eventId}")
    suspend fun getOffer(
        @Path("eventId") id: Int
    ): Response<OfferResponse>

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/Offert")
    suspend fun getOfferByDate(
        @Query("dateTime") date: String
    ): Response<OfferResponse>

    @Headers("accept: */*", "Content-Type: application/json")
    @PATCH("/Offert/{offertId}")
    suspend fun patchOffer(
        @Header("authorization") authorization: String,
        @Path("offertId") id: Int,
        @Body requestBody: RequestBody
    ):Response<JsonObject>

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/User/scoreBoard")
    suspend fun getLeaderboard(@Header("authorization") authorization: String): Response<RankingResponse>

    @Headers("accept: */*", "Content-Type: application/json")
    @POST("/Coupon")
    suspend fun postCoupon(@Header("authorization") authorization: String, @Body requestBody: RequestBody): Response<JsonObject>

    @Headers("accept: */*", "Content-Type: application/json")
    @GET("/User/Coupon")
    suspend fun getCoupons(@Header("authorization") authorization: String): Response<CouponsResponse>

    @Headers("accept: */*", "Content-Type: application/json")
    @DELETE("/User")
    suspend fun deleteUser(@Header("authorization") authorization: String): Response<JsonObject>

}