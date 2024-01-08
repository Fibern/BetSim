package com.example.betsim.presentation.modify.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Odd
import com.example.betsim.presentation.common.components.BetSimOutlinedTextField

@Composable
fun ModifyMatchType(options: List<Odd>) {

    for (i in 0..1) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = options[i].name,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 32.dp)
            )
            BetSimOutlinedTextField(
                value = options[i].odd.toString(),
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 32.dp)
            )

        }
    }

}