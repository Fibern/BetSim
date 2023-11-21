package com.example.betsim.presentation.user_main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.betsim.presentation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetSimTopAppBar(){

    TopAppBar(
        title = { Text("username") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BetSimBottomAppBar(navController: NavController, onClick: (Boolean) -> Unit){


    val items = listOf(
        BottomNavigationItem(
            title = "Dzisiaj",
            selectedIcon = Icons.Filled.Today,
            unselectedIcon = Icons.Outlined.Today,
            hasNews = false,
            badgeCount = null,
            hide = false,
            route = Screen.TodayEventsScreen.route
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
            title = "Profil",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
            badgeCount = null,
            hide = true,
            route = Screen.SettingsScreen.route
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1)
    }


    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.onPrimary.copy(0.15f).compositeOver(MaterialTheme.colorScheme.primary)
                ),
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    onClick(item.hide)
                    selectedItemIndex = index
                    navController.navigate(item.route){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
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

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val hide: Boolean,
    val route: String
)
