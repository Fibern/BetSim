package com.example.betsim.repository

import com.example.betsim.data.model.OddItem
import com.example.betsim.data.remote.BetSimApi
import com.example.betsim.data.remote.error_responses.ErrorResponse
import com.example.betsim.data.remote.responses.EventsResponse
import com.example.betsim.data.remote.responses.LoginResponse
import com.example.betsim.data.remote.responses.OfferResponse
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.data.remote.status.RegisterStatus
import com.example.betsim.data.remote.responses.User
import com.google.gson.Gson
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject





@ActivityScoped
class BetSimRepository @Inject constructor(
    private val api: BetSimApi
) {

    private val type = MediaType.parse("application/json")
    private val gson = Gson()


    suspend fun register(username: String, email: String, password: String ): RegisterStatus {
        val map = hashMapOf(
                "username" to username,
                "email" to email,
                "password" to password
        )
        try {
            val response = api.register(RequestBody.create(type,gson.toJson(map)))
            if (response.isSuccessful){
                return RegisterStatus.Success
            }else{
                val error = response.errorBody()?.string()?: return RegisterStatus.Unknown
                val errorResponse = gson.fromJson(error, ErrorResponse::class.java)
                val errorCodes = errorResponse.errors.map { it.code }
                if (errorCodes.size > 1) return RegisterStatus.DuplicateEmailAndUsername
                if (errorCodes[0] == "DuplicateUserName") return RegisterStatus.DuplicateUsername
                if (errorCodes[0] == "DuplicateEmail") return RegisterStatus.DuplicateEmail
                return RegisterStatus.Unknown
            }
        }catch (e: Exception){
            return RegisterStatus.BadInternet
        }
    }

    suspend fun login(username: String, password: String): BasicStatus<LoginResponse> {
        val map = hashMapOf(
            "email" to username,
            "password" to password
        )
        try{
            val response = api.login(RequestBody.create(type, gson.toJson(map)))
            if (response.isSuccessful)
                return BasicStatus.Success(response.body()!!)
            return BasicStatus.Failure
        }catch (e: Exception){
            return BasicStatus.BadInternet
        }
    }

    suspend fun refresh(token: String): BasicStatus<LoginResponse> {
        val map = hashMapOf("refreshToken" to token)
        try {
            val response = api.refresh(RequestBody.create(type, gson.toJson(map)))
            if (response.isSuccessful)
                return BasicStatus.Success(response.body()!!)
            return BasicStatus.Failure
        }catch (e: Exception){
            return BasicStatus.BadInternet
        }
    }

    suspend fun getUser(token: String): BasicStatus<User> {
        try {
            val response = api.getUser("Bearer $token")
            if (response.isSuccessful)
                return BasicStatus.Success(response.body()!!.user)
            return BasicStatus.Failure
        }catch (e: Exception){
            return BasicStatus.BadInternet
        }
    }

    suspend fun getEvents(): BasicStatus<EventsResponse>{
        try {
            val response = api.getEvents()
            if (response.isSuccessful)
                return BasicStatus.Success(response.body()!!)
            return BasicStatus.Failure
        }catch (e: Exception){
            return BasicStatus.BadInternet
        }
    }

    suspend fun getEventsByUser(token: String): BasicStatus<EventsResponse>{
        try {
            val response = api.getEventByUser("Bearer $token")
            if (response.isSuccessful)
                return BasicStatus.Success(response.body()!!)
            return BasicStatus.Failure
        }catch (e: Exception){
            return BasicStatus.BadInternet
        }
    }

    suspend fun postEvent(token: String, title: String, icon: String): Boolean{
        val map = hashMapOf(
            "title" to title,
            "icon" to icon
        )
        return try {
            val response = api.postEvent(
                "Bearer $token",
                RequestBody.create(type, gson.toJson(map))
            )
            response.isSuccessful
        }catch (e: Exception){
            false
        }
    }

    suspend fun deleteEvent(token: String, id: Int): Boolean{
        return try {
            val response = api.deleteEvent("Bearer $token", id)
            response.isSuccessful
        }catch (e: Exception){
            false
        }
    }

    suspend fun postOffer(
        token: String,
        id: Int,
        title: String,
        offerType: Int,
        dateTime: String,
        odds: List<OddItem>
    ): Boolean{
        val map = hashMapOf(
            "title" to title,
            "type" to offerType,
            "dateTime" to dateTime,
            "odds" to odds
        )
        return try {
            val response = api.postOffer("Bearer $token", id, RequestBody.create(type, gson.toJson(map)))
            response.isSuccessful
        }catch (e: Exception){
            false
        }
    }

    suspend fun getOffer(id: Int): BasicStatus<OfferResponse>{
        try {
            val response = api.getOffer(id)
            if (response.isSuccessful)
                return BasicStatus.Success(response.body()!!)
            return BasicStatus.Failure
        }catch (e: Exception){
            e.printStackTrace()
            return BasicStatus.BadInternet
        }
    }


}