package com.example.betsim.presentation.common.components

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class CouponStatusIcon {
    companion object {
        @Composable
        fun AwaitIcon() {
            Icon(
                imageVector = Icons.Filled.HourglassTop,
                contentDescription = "",
                tint = Color(0xFFDF8500)
            )
        }

        @Composable
        fun WinIcon() {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "",
                tint = Color.Green
            )
        }

        @Composable
        fun LoseIcon() {
            Icon(
                imageVector = Icons.Filled.Cancel,
                contentDescription = "",
                tint = Color.Red
            )
        }
    }
}