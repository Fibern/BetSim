package com.example.betsim.repository

import android.util.Log
import com.example.betsim.data.remote.BetSimApi
import com.example.betsim.data.remote.error_responses.ErrorResponse
import com.example.betsim.domain.response.LoginStatus
import com.example.betsim.domain.response.RegisterStatus
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


    suspend fun register(username: String, email: String, password: String ): RegisterStatus{
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

    suspend fun login(username: String, password: String): LoginStatus{
        val map = hashMapOf(
            "email" to username,
            "password" to password
        )
        try{
            val response = api.login(RequestBody.create(type, gson.toJson(map)))
            if (response.isSuccessful)
                return LoginStatus.Success(response.body()!!)
            return LoginStatus.Failure
        }catch (e: Exception){
            return LoginStatus.BadInternet
        }
    }

    suspend fun getUser(token: String){
        try {
            val response = api.getUser("Bearer $token")
            if (response.isSuccessful){
                Log.i("jd200", response.body()!!.asString)
            }else{
                Log.i("jd400", response.errorBody()!!.toString())
            }
        }catch (e: Exception){
            Log.i("jd400","nichuja")
        }
    }

    suspend fun refresh(token: String): LoginStatus{
        val map = hashMapOf("refreshToken" to token)
        try {
            val response = api.refresh(RequestBody.create(type, gson.toJson(map)))
            if (response.isSuccessful)
                return LoginStatus.Success(response.body()!!)
            return LoginStatus.Failure
        }catch (e: Exception){
            return LoginStatus.BadInternet
        }
    }

}