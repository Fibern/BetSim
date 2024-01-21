package com.example.betsim.data.remote.responses

data class RankingUsers(
    val topUsers: List<TopUser>,
    val user: TopUser
)