package com.nimesh.vasani.speer_technologies_android.presentation.uistate

import com.nimesh.vasani.speer_technologies_android.data.model.User

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentUser: User? = null,
    val isUserLoggedIn:Boolean = false,
    val success: Boolean = false
)