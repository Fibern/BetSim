package com.example.betsim.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.betsim.data.remote.LoginResponse

class SecurePreferencesHelper(context: Context){

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "preferences",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun deleteLoginResponse(){
        sharedPreferences.edit().remove("refresh_token").remove("access_token").apply()
    }
    fun saveLoginResponse(loginResponse: LoginResponse) {
        sharedPreferences.edit()
            .putString("refresh_token", loginResponse.refreshToken)
            .putString("access_token", loginResponse.refreshToken)
            .apply()
    }

    fun getLoginResponse(): LoginResponse? {
        val refresh = sharedPreferences.getString("refresh_token", null)
        val access = sharedPreferences.getString("access_token", null)
        if (refresh.isNullOrBlank() || access.isNullOrBlank()) return null
        return LoginResponse(accessToken = access, refreshToken = refresh, expiresIn = 0, tokenType = "")
    }

}
