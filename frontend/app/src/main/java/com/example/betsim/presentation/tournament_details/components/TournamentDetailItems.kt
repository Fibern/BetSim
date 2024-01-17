package com.example.betsim.presentation.tournament_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame
import java.time.format.DateTimeFormatter
import kotlin.math.min

@Composable
fun TournamentDetailChoice(
    game: TournamentGame,
    isMod: Boolean,
    onClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(bottom = 16.dp)
            .clickable(isMod) { onClick(game.id) },
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
                DateTimeFormatter.ofPattern("yyyy.MM.dd").format(game.date),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
            )
            Text(
                DateTimeFormatter.ofPattern("HH:mm:ss").format(game.date),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
            )
        }

        Text(
            text = game.name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.height(12.dp))

        for (i in 0 until game.odds.size step 3) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                for (j in i..min(i+2,game.odds.size-1)) {
                    val it = game.odds[j]
                    TournamentItemButton(
                        isMod = isMod,
                        onClick = { onClick(j) },
                        name = it.name,
                        odd = it.odd,
                        selected = j == game.selected.value
                    )
                }
            }
        }
    }

}


@Preview
@Composable
fun TournamentDetailItemPreview(){
    TournamentDetailChoice(
        game = TournamentGame(
            1,
            "asd - bsd",
            listOf(Odd(1,"asd","1.23"),Odd(2,"asd2","1.53"),Odd(3,"as3d","1.33"),Odd(3,"as3d","1.33"))
        ),
        false,
        onClick = {}
    )
}
