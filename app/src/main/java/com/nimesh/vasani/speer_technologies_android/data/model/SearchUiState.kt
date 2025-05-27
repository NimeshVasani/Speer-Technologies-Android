package com.nimesh.vasani.speer_technologies_android.data.model

data class SearchUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var posts: List<Posts> = emptyList(),
    var currentUser : User? = null,
)
