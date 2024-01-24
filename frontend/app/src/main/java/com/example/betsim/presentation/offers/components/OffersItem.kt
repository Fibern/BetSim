package com.example.betsim.presentation.offers.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.data.remote.responses.Odd
import com.example.betsim.data.remote.responses.Offer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.min

@Composable
fun OffersItem(
    offer: Offer,
    isMod: Boolean,
    onClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colorScheme.secondary)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                DateTimeFormatter.ofPattern("yyyy.MM.dd").format(offer.dateTime.toLocalDate()),
                color = colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
            )
            Text(
                DateTimeFormatter.ofPattern("HH:mm:ss").format(offer.dateTime.toLocalTime()),
                color = colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
            )
        }

        Text(
            text = offer.title,
            textAlign = TextAlign.Center,
            color = colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        for (i in 0 until offer.odds.size step 3) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                for (j in i..min(i+2,offer.odds.size-1)) {
                    val it = offer.odds[j]
                    OffersItemButton(
                        isMod = isMod,
                        onClick = { onClick(j) },
                        name = it.playerName,
                        odd = it.oddValue.toString(),
                        selected = j == offer.selected
                    )
                }
            }
        }

        if (isMod && offer.dateTime.isBefore(LocalDateTime.now()) && offer.active){

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = { onClick(offer.id) },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colorScheme.onSecondary
                    ),
                    border = BorderStroke(1.dp, colorScheme.onSecondary)
                ) {
                    Text(text = "Dodaj wynik")
                }
                Button(
                    onClick = { /*TODO*/  },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.secondaryContainer,
                        contentColor = colorScheme.onSecondaryContainer
                    )
                ) {
                    Text(text = "Usuń")
                }
            }

        }

    }

}


@Preview
@Composable
fun OffersItemPreview(){
    OffersItem(
        offer = Offer(
            active = true,
            dateTime = LocalDateTime.now(),
            1,
            odds = listOf(Odd(1,1.23,"asd"),Odd(2,1.53,"asd2"),Odd(3,1.33,"as3d"),Odd(3,1.33,"as3d")),
            score = "",
            type = 0,
            winner = -1,
            selected = 0,
            title = "jd"
            ),
        true,
        onClick = {}
    )
}
