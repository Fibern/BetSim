package com.example.betsim.presentation.main.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.betsim.R.drawable.ic_casino_chip
import com.example.betsim.data.remote.responses.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetSimTopAppBar(
    user: User,
    onClick: () -> Unit
){

    var rotationState by remember { mutableFloatStateOf(0f) }

   val rotation by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = tween(1000), label = "rotate",
    )

    TopAppBar(
        title = { Text(user.username) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            if (!user.isMod && user.points != null) {
                Row(
                    modifier = Modifier
                        .clickable {
                            rotationState += 360f
                            onClick()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "%.2f".format(user.points).replace('.', ','),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Icon(
                        painterResource(id = ic_casino_chip),
                        "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .rotate(rotation)
                    )
                }
            }
        }
    )
}