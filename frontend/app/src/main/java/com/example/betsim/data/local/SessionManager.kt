package com.example.betsim.data.local

import com.example.betsim.data.remote.responses.LoginResponse
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.repository.BetSimRepository
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val helper: SecurePreferencesHelper,
    private val repository: BetSimRepository
) {

    private var currentLogin: LoginResponse? = null

    init {
        currentLogin = helper.getLoginResponse()
    }

    fun saveLoginResponse(loginResponse: LoginResponse) {
        currentLogin = loginResponse
        helper.saveLoginResponse(loginResponse)
    }

    fun getCurrent(): LoginResponse? {
        return currentLogin
    }

    fun clearSession() {
        helper.deleteLoginResponse()
        currentLogin = null
    }

    suspend fun getRefresh(): LoginResponse?{
        val login = helper.getLoginResponse() ?: return null
        return when(val result = repository.refresh(login.refreshToken)){
            is BasicStatus.Success -> {
                saveLoginResponse(result.response)
                result.response
            }
            else -> null
        }
    }

}