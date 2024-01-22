package com.example.betsim.presentation.leaderboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betsim.data.local.SessionManager
import com.example.betsim.data.remote.responses.RankingUsers
import com.example.betsim.data.remote.status.BasicStatus
import com.example.betsim.repository.BetSimRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderBoardViewModel @Inject constructor(
    repository: BetSimRepository,
    sessionManager: SessionManager,
): ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _rankingUsers = mutableStateOf<RankingUsers?>(null)
    val rankingUsers: State<RankingUsers?> = _rankingUsers

    init {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val login = sessionManager.getCurrent()
                if (login != null) {
                    when (val response = repository.getLeaderboard(login.accessToken)) {
                        BasicStatus.BadInternet -> {}
                        BasicStatus.Failure -> {}
                        is BasicStatus.Success -> {
                            _rankingUsers.value = response.response.user
                        }
                    }
                }
                _isLoading.value = false
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}