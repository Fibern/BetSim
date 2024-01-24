package com.example.betsim.presentation.coupon_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip

@Composable
fun CouponDetailsBottomBar(odd: Double, value: Double, winnings: Double, status: Int){

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
            trailingContent = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = value.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painterResource(id = ic_casino_chip),
                        "",
                    )
                }
            }
        )
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.primary,
                headlineColor =  MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            headlineContent = { Text(text = if(status != 1) "Wygrana" else "Potencjalna wygrana") },
            trailingContent = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = if(status != 0) winnings.toString() else "0")
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painterResource(id = ic_casino_chip),
                        "",
                    )
                }
            }
        )

    }

}