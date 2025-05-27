package com.nimesh.vasani.speer_technologies_android.presentation.navigation

import com.nimesh.vasani.speer_technologies_android.data.model.Posts
import com.nimesh.vasani.speer_technologies_android.data.model.User
import kotlinx.serialization.Serializable


@Serializable
object HomeScreen

@Serializable
data class UsersScreen(val user: User)

@Serializable
object LoginScreen

@Serializable
object SignUpScreen

@Serializable
object ProfileScreen

@Serializable
data class FollowersScreen(var followers : List<User>)

@Serializable
object SearchScreen

@Serializable
data class DetailScreen(val post: Posts,)

