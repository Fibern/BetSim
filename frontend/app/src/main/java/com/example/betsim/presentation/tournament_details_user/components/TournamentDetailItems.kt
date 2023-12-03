package com.example.betsim.presentation.tournament_details_user.components

import android.icu.text.SimpleDateFormat
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame
import java.util.Locale

@Composable
fun TournamentDetailChoice(
    game: TournamentGame,
    onClick: (Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN).format(game.date),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
            )
            Text(
                SimpleDateFormat("hh:mm:ss", Locale.GERMAN).format(game.date),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing
            )
        }

        Text(
            text = "${game.homeTeam} - ${game.awayTeam}",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){

            game.odds.forEachIndexed{ index: Int, it: Odd ->
                Button(
                    onClick = {
                        onClick(index)
                    },
                    modifier = Modifier
                        .width(90.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(index == game.selected.value) Color.Black else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Column {
                        Text(
                            text = it.name,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                            lineHeight = MaterialTheme.typography.labelSmall.lineHeight,
                            letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = it.odd.toString(),
                            textAlign = TextAlign.Center
                        )
                    }
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
            "asd",
            "bsd",
            listOf(Odd(1,"asd",1.23),Odd(2,"asd2",1.53),Odd(3,"as3d",1.33))
        ),
        onClick = {}
    )
}
