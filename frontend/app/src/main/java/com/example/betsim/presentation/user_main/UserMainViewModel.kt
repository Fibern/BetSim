package com.example.betsim.presentation.user_main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.betsim.presentation.tournament_details_user.TournamentGamesState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserMainViewModel @Inject constructor(

) : ViewModel() {

    private var _state = mutableStateOf(TournamentGamesState())
    val state = _state

}