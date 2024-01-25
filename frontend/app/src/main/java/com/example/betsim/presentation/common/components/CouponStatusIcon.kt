package com.example.betsim.presentation.common.components

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class CouponStatus {

    Lose {
        @Composable
        override fun GetIcon() {
            Icon(
                imageVector = Icons.Filled.Cancel,
                contentDescription = "",
                tint = Color.Red
            )
        }
    },
    Await {
        @Composable
        override fun GetIcon() {
            Icon(
                imageVector = Icons.Filled.HourglassTop,
                contentDescription = "",
                tint = Color(0xFFDF8500)
            )
        }
    },
    Win {
        @Composable
        override fun GetIcon() {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "",
                tint = Color.Green
            )
        }
    };

    @Composable
    abstract fun GetIcon()

}