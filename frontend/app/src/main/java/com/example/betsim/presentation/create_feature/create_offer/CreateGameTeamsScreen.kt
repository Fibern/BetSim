package com.example.betsim.presentation.create_feature.create_offer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.betsim.domain.model.OfferType
import com.example.betsim.presentation.common.components.BetSimButton
import com.example.betsim.presentation.create_feature.CreationEvent
import com.example.betsim.presentation.create_feature.components.AddTeamsListItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateGameTeamsScreen(viewModel: CreateGameViewModel) {


    val drawable by remember { viewModel.drawable }
    val type by remember { viewModel.type }
    val remaining by remember { viewModel.remaining }
    val odds = viewModel.odds
    val createErrorText by remember { viewModel.createErrorText }

    var h by remember {
        mutableStateOf(0.dp)
    }
    val d = LocalDensity.current
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
            ) {


            LazyColumn(
                modifier = Modifier.padding(bottom = h),
            ) {
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Text(
                            text = "Lp.",
                            modifier = Modifier.width(20.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Nazwa wyboru",
                            modifier = Modifier.width(160.dp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Procent",
                            modifier = Modifier.width(80.dp),
                            textAlign = TextAlign.Center
                        )
                        if (type == OfferType.Selection) {
                            Text(
                                text = "Usuń",
                                modifier = Modifier.width(40.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                itemsIndexed(odds) { id, oddState ->

                    AddTeamsListItem(
                        offerType = type,
                        name = odds[id].name,
                        odd = odds[id].odd,
                        id = id,
                        onNameChange = { name ->
                            viewModel.onEvent(CreationEvent.EnteredTeamName(name, id))
                        },
                        onChanceChange = { odd ->
                            viewModel.onEvent(CreationEvent.EnteredWinChance(odd, id))
                        },
                        onRemove = {
                            viewModel.onEvent(CreationEvent.RemoveTeam(oddState))
                        }
                    )

                }
            }


            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned {
                        h = with(d) { it.size.height.toDp() }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = createErrorText,
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                Row {
                    when (type) {
                        OfferType.Match -> {
                            TextButton(onClick = {
                                viewModel.onEvent(CreationEvent.CheckBoxChange)
                            }) {
                                Text(text = "Mecz może zakończyć się remisem")
                            }
                            Checkbox(
                                checked = drawable,
                                onCheckedChange = {
                                    viewModel.onEvent(CreationEvent.CheckBoxChange)
                                }
                            )
                        }

                        OfferType.Selection -> {
                            TextButton(onClick = {
                                viewModel.onEvent(CreationEvent.AddTeam)
                            }) {
                                Icon(Icons.Filled.AddCircle, contentDescription = "")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Dodaj")
                            }
                        }
                    }
                }
                BetSimButton(text = "Utwórz") {
                    viewModel.onEvent(CreationEvent.CreateClick)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(text = "Pozostało: ${remaining}%")
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
        
    }

}

@Preview
@Composable
fun CreateGameTeamsScreenPreview(){
    CreateGameTeamsScreen(viewModel = hiltViewModel())
}

@Preview
@Composable
fun CreateGameTeamsScreenPreview2(){
    val viewModel = CreateGameViewModel()
    viewModel.onEvent(CreationEvent.SelectDropdown(offerType = OfferType.Selection))
    CreateGameTeamsScreen(viewModel)
}