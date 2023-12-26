package com.example.betsim.presentation.coupon_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun CouponDetailsBottomBar(odd: Double, value: Double, winnings: Double, finished: Boolean){

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary,
                headlineColor =  MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            headlineContent = { Text(text = "Łączny kurs") },
            trailingContent = { Text(text = odd.toString()) }
        )
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary,
                headlineColor =  MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            headlineContent = { Text(text = "Stawka") },
            trailingContent = { Text(text = value.toString()) }
        )
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary,
                headlineColor =  MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            headlineContent = { Text(text = if(finished) "Wygrana" else "Potencjalna wygrana") },
            trailingContent = { Text(text = winnings.toString()) }
        )

    }

}