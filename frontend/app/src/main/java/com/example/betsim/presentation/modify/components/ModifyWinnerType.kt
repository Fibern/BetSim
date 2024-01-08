package com.example.betsim.presentation.modify.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Odd
import com.example.betsim.presentation.common.components.BetSimDropdown

@Composable
fun ModifyWinnerType(options: List<Odd>) {

    Row (
        modifier = Modifier.padding(bottom = 8.dp)
    ){
        BetSimDropdown(
            value = "",
            options = options.map { it.name }
        ) {

        }
    }
}
