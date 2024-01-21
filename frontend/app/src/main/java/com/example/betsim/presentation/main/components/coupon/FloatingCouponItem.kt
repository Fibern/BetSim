package com.example.betsim.presentation.main.components.coupon

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.data.remote.responses.Odd
import com.example.betsim.data.remote.responses.Offer
import java.time.LocalDateTime

@Composable
fun FloatingCouponItem(offer: Offer, onClick: () -> Unit){
    val odd = offer.odds[offer.selected!!]

    Row (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 4.dp)
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){


        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                odd.playerName,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
            Text(
                "TODO OfferName",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
            )
        }

        Row {

            Column(
                Modifier.fillMaxHeight().padding(end = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = odd.oddValue.toString().replace('.',','),
                    color = MaterialTheme.colorScheme.onPrimary)
            }

            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.clickable { onClick() }
                )
            }
        }
        
    }

}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun FlIt(){
    FloatingCouponItem(offer = Offer(true, LocalDateTime.now(), 1, listOf(Odd(1, 1.2, "Team 1"), Odd(2, 1.3, "Team 3")), "", 0, -1, 0)){

    }
}