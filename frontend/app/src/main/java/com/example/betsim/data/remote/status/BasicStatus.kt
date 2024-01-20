package com.example.betsim.data.remote.status

sealed class BasicStatus<out T> {
    data class Success<T>(val response: T): BasicStatus<T>()
    data object Failure: BasicStatus<Nothing>()
    data object BadInternet: BasicStatus<Nothing>()
}