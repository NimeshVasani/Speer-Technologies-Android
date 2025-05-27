package com.nimesh.vasani.speer_technologies_android.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    var posts : List<Posts>,
)
