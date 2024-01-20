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

    suspend fun getCurrentLogin(): LoginResponse? {
        return if (refresh()) currentLogin
               else null
    }

    fun clearSession() {
        helper.deleteLoginResponse()
        currentLogin = null
    }

    private suspend fun refresh(): Boolean{
        val login = helper.getLoginResponse() ?: return false
        return when(val result = repository.refresh(login.refreshToken)){
            is BasicStatus.Success -> {
                saveLoginResponse(result.response)
                true
            }
            else -> {
                false
            }
        }
    }

}