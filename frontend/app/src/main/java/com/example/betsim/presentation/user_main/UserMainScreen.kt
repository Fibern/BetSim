package com.example.betsim.presentation.user_main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.betsim.presentation.Screen
import com.example.betsim.presentation.coupons.CouponsScreen
import com.example.betsim.presentation.event_details_user.EventDetailScreen
import com.example.betsim.presentation.events_user.EventsUserScreen
import com.example.betsim.presentation.leaderboard.LeaderboardScreen
import com.example.betsim.presentation.settings.SettingsScreen
import com.example.betsim.presentation.user_main.components.CouponPreview


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val hide: Boolean,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun UserMainScreen(
   // viewModel: UserMainViewModel = hiltViewModel()
){

    val items = listOf(
        BottomNavigationItem(
            title = "Dzisiaj",
            selectedIcon = Icons.Filled.Today,
            unselectedIcon = Icons.Outlined.Today,
            hasNews = false,
            badgeCount = null,
            hide = false,
            route = Screen.EventsScreen.route
        ),
        BottomNavigationItem(
            title = "Wszystko",
            selectedIcon = Icons.Filled.CalendarMonth,
            unselectedIcon = Icons.Outlined.CalendarMonth,
            hasNews = false,
            badgeCount = null,
            hide = false,
            route = Screen.EventsScreen.route
        ),
        BottomNavigationItem(
            title = "Kupony",
            selectedIcon = Icons.Filled.Receipt,
            unselectedIcon = Icons.Outlined.Receipt,
            hasNews = false,
            badgeCount = null,
            hide = false,
            route = Screen.CouponsScreen.route
        ),
        BottomNavigationItem(
            title = "Ranking",
            selectedIcon = Icons.Filled.Leaderboard,
            unselectedIcon = Icons.Outlined.Leaderboard,
            hasNews = false,
            badgeCount = null,
            hide = true,
            route = Screen.LeaderboardScreen.route,
        ),
        BottomNavigationItem(
            title = "Ustawienia",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = false,
            badgeCount = null,
            hide = true,
            route = Screen.SettingsScreen.route
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1)
    }
    val navController = rememberNavController()


    var collapsed by remember{
        mutableStateOf(true)
    }
    var hidden by remember{
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            ) { collapsed = true },

        topBar = {
            TopAppBar(
                title = { Text("username") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) },

        floatingActionButton = {

            AnimatedVisibility(
                visible = !hidden,
                enter = fadeIn(tween(150)),
                exit = fadeOut(tween(150))
            ) {

                AnimatedContent(
                    label = "",
                    targetState = collapsed ,
                    content = {
                        if(it){

                            FloatingActionButton(
                                onClick = {/*TODO*/
                                    collapsed = false
                                },
                                containerColor = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(top = 500.dp, start = 200.dp)
                            ){
                                BadgedBox(
                                    badge = {
                                        Badge {
                                            Text(text = "0")
                                        }
                                    }
                                ) {
                                    Icon(Icons.Filled.ReceiptLong, "kupon", tint = MaterialTheme.colorScheme.onPrimary )
                                }

                            }
                        }else{
                            CouponPreview()
                        }
                    },
                    transitionSpec = {
                        if(hidden)
                            slideIn(
                                initialOffset = {IntOffset(it.width, it.height)}, animationSpec = tween(delayMillis = 500)
                            ) + fadeIn(
                                animationSpec = tween(delayMillis = 500)
                            ) togetherWith slideOut(
                                targetOffset = {IntOffset(it.width, it.height)}, animationSpec = tween(durationMillis = 500)
                            ) + fadeOut(tween(durationMillis = 500))
                        else
                            slideIn(
                                initialOffset = {IntOffset(it.width, it.height)}
                            ) + fadeIn(

                            ) togetherWith  slideOut(
                                targetOffset = {IntOffset(it.width, it.height)}, animationSpec = tween(durationMillis = 500)
                            ) + fadeOut(
                                tween(durationMillis = 500)
                            )
                    }
                )

            }

        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.onPrimary.copy(0.15f).compositeOver(MaterialTheme.colorScheme.primary)
                        ),
                        selected = selectedItemIndex == index,
                        onClick = {
                            hidden = item.hide
                            collapsed = true
                            selectedItemIndex = index
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(text = item.title, color = MaterialTheme.colorScheme.onPrimary)
                        },
                        alwaysShowLabel = false,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = item.title,
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.EventsScreen.route,
            Modifier.padding(innerPadding)
            ){
            composable(Screen.EventsScreen.route) { EventsUserScreen() }
            composable(Screen.EventDetailScreen.route) { EventDetailScreen() }
            composable(Screen.CouponsScreen.route) { CouponsScreen() }
            composable(Screen.LeaderboardScreen.route) { LeaderboardScreen() }
            composable(Screen.SettingsScreen.route) { SettingsScreen() }
        }

    }

}

@Preview
@Composable
fun UserMainPreview(){
    UserMainScreen()
}