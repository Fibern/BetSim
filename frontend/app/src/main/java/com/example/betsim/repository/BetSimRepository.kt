package com.example.betsim.repository

import android.util.Log
import com.example.betsim.data.remote.BetSimApi
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


    suspend fun register(username: String, email: String, password: String ){
        val response = try {
            api.register(
                RequestBody.create(
                    type,
                    Gson().toJson(
                        hashMapOf(
                            "username" to username,
                            "email" to email,
                            "password" to password
                        )
                    )
                )
            )
        }catch (e: Exception){
            Log.i("jd", e.toString())
        }
        Log.i("jd", response.toString())
    }

    suspend fun login(username: String, password: String){
        val response = try {
            api.login(
                RequestBody.create(
                    type,
                    Gson().toJson(
                        hashMapOf(
                            "username" to username,
                            "password" to password
                        )
                    )
                )
            )
        }catch (e: HttpException){
            Log.i("jd", e.toString())
        }
        Log.i("jd", response.toString())
    }

}