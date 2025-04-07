package com.nimesh.vasani.speer_technologies_android.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nimesh.vasani.speer_technologies_android.presentation.screens.HomeScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.LoginScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.ProfileScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.SignUpScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.UsersScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0XFFFAF9F6),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) } // Adding SnackbarHost

    ) { _ ->

        NavHost(
            navController = navController,
            startDestination =  ProfileScreen
        ) {
            composable<HomeScreen> {
                HomeScreen (

                    onUsersClick = {
                        navController.navigate(UsersScreen) {
                            launchSingleTop = true

                        }
                    }
                )
            }
            composable<LoginScreen> {

                LoginScreen(
                    onSignInClick = {email, password ->
                        focusManager.clearFocus()
                        navController.navigate(HomeScreen)
                    },
                    onSignUpClick = {
                        navController.navigate(SignUpScreen) {
                            popUpTo(SignUpScreen) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<SignUpScreen> {
                SignUpScreen(
                    onSignInClick = { email, password ->

                    }
                )

            }
            composable<ProfileScreen> {
                ProfileScreen(
                    onLoginClick = {

                        navController.navigate(HomeScreen) {
                            popUpTo(HomeScreen) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(SignUpScreen) {
                            popUpTo(SignUpScreen) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<UsersScreen>(
                enterTransition = {
                    slideIntoContainer(
                        animationSpec = tween(700),
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(700)
                    )
                }
            ) {
                UsersScreen()
            }
        }
    }

}