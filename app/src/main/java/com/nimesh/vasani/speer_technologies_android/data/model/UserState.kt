package com.nimesh.vasani.speer_technologies_android.data.model

data class UserState(

    val isLoading: Boolean = false,
    val error: String? = null,
    var users: List<User> = emptyList(),
    var currentUser : User? = null,
)