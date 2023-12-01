package com.example.betsim.presentation.tournament_details_user.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.betsim.domain.model.Odd
import com.example.betsim.domain.model.TournamentGame

@Composable
fun TournamentDetailChoice(
    game: TournamentGame,
    onClick: (TournamentGame) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "${game.homeTeam} - ${game.awayTeam}",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier.fillMaxWidth()
        ){

            Spacer(modifier = Modifier.weight(1f))

            game.odds.forEachIndexed(){ index: Int, it: Odd ->
                Button(
                    onClick = {
                        /*TODO*/
                              game.selected.value = index
                              onClick(game)
                    },
                    modifier = Modifier
                        .width(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(index == game.selected.value) Color.Black else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = it.odd.toString())
                }
                Spacer(modifier = Modifier.weight(1f))
            }

        }

    }

}