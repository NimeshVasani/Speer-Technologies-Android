package com.nimesh.vasani.speer_technologies_android.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.presentation.screens.FollowersScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.HomeScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.LoginScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.ProfileScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.SignUpScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.UsersScreen
import com.nimesh.vasani.speer_technologies_android.presentation.viewmodels.UsersViewmodel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    usersViewModel: UsersViewmodel = koinViewModel()
) {
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val currentUser = usersViewModel.currentUser.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0XFFFAF9F6),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) } // Adding SnackbarHost

    ) { _ ->

        NavHost(
            navController = navController,
            startDestination = ProfileScreen
        ) {
            composable<HomeScreen> {
                HomeScreen(

                    onUsersClick = { user ->
                        usersViewModel.setCurrentUser(user)
                        navController.navigate(UsersScreen) {
                            launchSingleTop = true

                        }
                    }
                )
            }
            composable<LoginScreen> {

                LoginScreen(
                    onSignInClick = { email, password ->
                        focusManager.clearFocus()
                        navController.navigate(HomeScreen) {
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
            ) {navBackStackEntry ->

                LaunchedEffect(currentUser.value) {
                    currentUser.value?.followers_url?.let { usersViewModel.getFollowers(it) }
                    currentUser.value?.following_url?.let { usersViewModel.getFollowing(it) }
                    currentUser.value?.repos_url?.let { usersViewModel.getRepos(it) }
                }



                UsersScreen(
                    usersViewmodel = usersViewModel,
                    onFollowersClick = {
                        navController.navigate(FollowersScreen)
                    },

                )
            }

            composable<FollowersScreen>(
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
                FollowersScreen(
                    followers = currentUser.value?.followers ?: emptyList(),
                    onFollowersClick = { user ->
                        usersViewModel.setCurrentUser(user)
                        navController.navigate(UsersScreen) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

}