package com.example.betsim.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.SportsMma
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.SportsVolleyball
import androidx.compose.ui.graphics.vector.ImageVector

enum class EventIcons(val iconName: String, val icon: ImageVector) {

    Football("piłka nożna", Icons.Filled.SportsSoccer),
    Basketball("koszykówka", Icons.Filled.SportsBasketball),
    Volleyball("siatkówka", Icons.Filled.SportsVolleyball),
    Exports("e-sport", Icons.Filled.SportsEsports),
    Motorsports("wyścigi", Icons.Filled.SportsMotorsports),
    Mma("sport walki", Icons.Filled.SportsMma)

}