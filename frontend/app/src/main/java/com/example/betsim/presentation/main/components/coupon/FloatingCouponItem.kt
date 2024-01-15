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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame

@Composable
fun FloatingCouponItem(game: TournamentGame, onClick: () -> Unit){
    val odd = game.odds[game.selected.value!!]

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
                odd.name,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
            )
            Text(
                game.name,
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
                    text = odd.odd,
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
    FloatingCouponItem(game = TournamentGame(
        1, "aaa - bbb", listOf(Odd(1,"Team 1", "1.2"), Odd(2, "Team 3", "1.5")), selected = mutableStateOf(1)
    )){

    }
}