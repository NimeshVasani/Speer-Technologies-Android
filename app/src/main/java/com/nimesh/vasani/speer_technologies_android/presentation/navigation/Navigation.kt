package com.nimesh.vasani.speer_technologies_android.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class BottomNavItem(
    val route: Any,
    val selectedIcon: ImageVector,  // Can be ImageVector or Painter
    val unSelectedIcon: Any,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem(HomeScreen, Icons.Filled.Home, Icons.Outlined.Home, "Home"),
    BottomNavItem(CallScreen, Icons.Filled.Search, Icons.Outlined.Search, "Explore"),
    BottomNavItem(AlertScreen, Icons.Filled.Notifications, Icons.Outlined.Notifications, "Alert"),
    BottomNavItem(ProfileScreen, Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, "Profile")

)

@Serializable
object HomeScreen

@Serializable
object ChattingScreen

@Serializable
object CallScreen

@Serializable
object ProfileScreen

@Serializable
object AlertScreen

@Serializable
object AiChatScreen

@Serializable
object LoginScreen

@Serializable
object SignUpScreen


