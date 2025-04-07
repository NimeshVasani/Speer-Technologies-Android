package com.nimesh.vasani.speer_technologies_android.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRepos(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
    val url: String,
)
