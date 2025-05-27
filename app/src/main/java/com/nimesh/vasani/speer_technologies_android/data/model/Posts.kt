package com.nimesh.vasani.speer_technologies_android.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Posts(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    var reactions: Reactions
)
