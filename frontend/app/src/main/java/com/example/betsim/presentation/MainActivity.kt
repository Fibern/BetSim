package com.example.betsim.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.betsim.presentation.ui.theme.BetSimTheme
import dagger.hilt.android.AndroidEntryPoint

/*
data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)


 val items = listOf(
                    BottomNavigationItem(
                        title = "Dzisiaj",
                        selectedIcon = Icons.Filled.Today,
                        unselectedIcon = Icons.Outlined.Today,
                        hasNews = false,
                        badgeCount = null
                    ),
                    BottomNavigationItem(
                        title = "Wszystko",
                        selectedIcon = Icons.Filled.CalendarMonth,
                        unselectedIcon = Icons.Outlined.CalendarMonth,
                        hasNews = false,
                        badgeCount = null
                    ),
                    BottomNavigationItem(
                        title = "Ranking",
                        selectedIcon = Icons.Filled.Leaderboard,
                        unselectedIcon = Icons.Outlined.Leaderboard,
                        hasNews = false,
                        badgeCount = null
                    ),
                    BottomNavigationItem(
                        title = "Ustawienia",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        hasNews = false,
                        badgeCount = null
                    )
                )
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                                  selectedItemIndex = index
                                                  },
                                        label = {
                                                Text(text = item.title)
                                        },
                                        alwaysShowLabel = false,
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (item.badgeCount != null){
                                                        Badge {
                                                            Text(text = item.badgeCount.toString())
                                                        }
                                                    }else if(item.hasNews){
                                                        Badge()
                                                    }
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (index == selectedItemIndex) item.selectedIcon
                                                                  else item.unselectedIcon,
                                                    contentDescription = item.title
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) {

                    }

 */


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BetSimTheme {
                BetSimSurface()
            }
        }
    }
}