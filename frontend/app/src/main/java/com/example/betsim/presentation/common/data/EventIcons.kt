package com.example.betsim.presentation.common.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.SportsMma
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

enum class EventIcons(val iconName: String) {

    Football("piłka nożna") {
        @Composable
        override fun GetIcon() {
            return Icon(Icons.Filled.SportsSoccer, name)
        }
    },
    Basketball("koszykówka") {
        @Composable
        override fun GetIcon() {
            return Icon(Icons.Filled.SportsBasketball, name)
        }
    },
    Volleyball("siatkówka") {
        @Composable
        override fun GetIcon() {
            return Icon(Icons.Filled.SportsVolleyball, name)
        }
    },
    Exports("e-sport") {
        @Composable
        override fun GetIcon() {
            return Icon(Icons.Filled.SportsEsports, name)
        }
    },
    Motorsports("wyścigi") {
        @Composable
        override fun GetIcon() {
            return Icon(Icons.Filled.SportsMotorsports, name)
        }
    },
    Mma("sport walki") {
        @Composable
        override fun GetIcon() {
            return Icon(Icons.Filled.SportsMma, name)
        }
    };

    @Composable
    abstract fun GetIcon()

}