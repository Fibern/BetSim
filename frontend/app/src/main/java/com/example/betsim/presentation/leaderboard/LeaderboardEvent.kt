package com.example.betsim.presentation.leaderboard

sealed class LeaderboardEvent {
    data object Refresh: LeaderboardEvent()
}