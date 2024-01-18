package com.example.betsim.repository

import android.util.Log
import com.example.betsim.data.remote.BetSimApi
import com.example.betsim.data.remote.error_responses.ErrorResponse
import com.example.betsim.domain.response.RegisterError
import com.google.gson.Gson
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.HttpException
import javax.inject.Inject





@ActivityScoped
class BetSimRepository @Inject constructor(
    private val api: BetSimApi
) {

    private val type = MediaType.parse("application/json")


    suspend fun register(username: String, email: String, password: String ): RegisterError{
        val map = hashMapOf(
                "username" to username,
                "email" to email,
                "password" to password
        )
        try {
            val response = api.register(RequestBody.create(type,Gson().toJson(map)))
            if (response.isSuccessful){
                return RegisterError.Success
            }else{
                val error = response.errorBody()?.string()?: return RegisterError.Unknown
                val errorResponse = Gson().fromJson(error, ErrorResponse::class.java)
                val errorCodes = errorResponse.errors.map { it.code }
                if (errorCodes.size > 1) return RegisterError.DuplicateEmailAndUsername
                if (errorCodes[0] == "DuplicateUserName") return RegisterError.DuplicateUsername
                if (errorCodes[0] == "DuplicateEmail") return RegisterError.DuplicateEmail
                return RegisterError.Unknown
            }
        }catch (e: Exception){
            return RegisterError.BadInternet
        }
    }

    suspend fun login(username: String, password: String){
        val map = hashMapOf(
            "username" to username,
            "password" to password
        )
        val response = try {
            api.login(RequestBody.create(type, Gson().toJson(map))
            )
        }catch (e: HttpException){
            Log.i("jd", e.toString())
        }
        Log.i("jd", response.toString())
    }

}