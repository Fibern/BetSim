package com.example.betsim.data.local

import com.example.betsim.data.remote.responses.LoginResponse
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.repository.BetSimRepository
import java.time.LocalDateTime
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val helper: SecurePreferencesHelper,
    private val repository: BetSimRepository
) {

    private var currentLogin: LoginResponse? = null
    private var expires: LocalDateTime = LocalDateTime.now()

    init {
        currentLogin = helper.getLoginResponse()
    }

    fun saveLoginResponse(loginResponse: LoginResponse) {
        currentLogin = loginResponse
        expires = LocalDateTime.now().plusMinutes(55)
        helper.saveLoginResponse(loginResponse)
    }

    suspend fun getCurrent(): LoginResponse? {
        if (LocalDateTime.now().isAfter(expires))
            return getRefresh()
        return currentLogin
    }

    fun clearSession() {
        helper.deleteLoginResponse()
        currentLogin = null
    }

    private suspend fun getRefresh(): LoginResponse?{
        val login = currentLogin ?: helper.getLoginResponse() ?: return null
        return when(val result = repository.refresh(login.refreshToken)){
            is BasicStatus.Success -> {
                saveLoginResponse(result.response)
                result.response
            }
            else -> null
        }
    }

}