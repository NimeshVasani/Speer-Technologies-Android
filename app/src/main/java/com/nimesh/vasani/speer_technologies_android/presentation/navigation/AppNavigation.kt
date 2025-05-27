package com.nimesh.vasani.speer_technologies_android.presentation.navigation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nimesh.vasani.speer_technologies_android.data.model.Posts
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.presentation.screens.DetailScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.FollowersScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.HomeScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.LoginScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.ProfileScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.SearchScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.SignUpScreen
import com.nimesh.vasani.speer_technologies_android.presentation.screens.UsersScreen
import com.nimesh.vasani.speer_technologies_android.presentation.viewmodels.SearchViewModel
import com.nimesh.vasani.speer_technologies_android.presentation.viewmodels.UsersViewmodel
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.typeOf

@Composable
fun AppNavigation(
    usersViewModel: UsersViewmodel = koinViewModel(),
    searchViewModel: SearchViewModel = koinViewModel<SearchViewModel>()
) {
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current
    val uiState by usersViewModel.uiState.collectAsStateWithLifecycle()

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
                SearchScreen(
                    searchViewmodel = searchViewModel,
                    onClick = {
                        navController.navigate(DetailScreen(post = it))
                    })
            }

            composable<DetailScreen> (
                typeMap = mapOf(typeOf<Posts>() to serializableType<Posts>())
            ){ backStack ->
                val post = backStack.toRoute<DetailScreen>().post
                DetailScreen(post = post, modifier = Modifier)
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
                typeMap = mapOf(typeOf<User>() to serializableType<User>())
            ) { navBackStackEntry ->
                val currentUser = navBackStackEntry.toRoute<UsersScreen>().user
                LaunchedEffect(currentUser) {
                    currentUser.followers_url?.let { usersViewModel.getFollowers(it, currentUser) }
                    currentUser.repos_url?.let { usersViewModel.getRepos(it, currentUser.login) }
                    Log.d("UsersScreen", uiState.users.size.toString())
                }

                UsersScreen(
                    usersViewmodel = usersViewModel,
                    onFollowersClick = { followers ->
                        navController.navigate(FollowersScreen(followers = followers))
                    },
                    login = currentUser.login
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
                },
                typeMap = mapOf(typeOf<List<User>>() to serializableType<List<User>>())

            ) { navBackStackEntry ->
                val followers = navBackStackEntry.toRoute<FollowersScreen>().followers

                FollowersScreen(
                    followers = followers,
                    onFollowersClick = { user ->
                        navController.navigate(
                            UsersScreen(
                                user
                            )
                        ) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

}

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = true,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let<String, T>(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: T): String = Uri.encode(Json.encodeToString(value))

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, json.encodeToString(value))
    }
}


