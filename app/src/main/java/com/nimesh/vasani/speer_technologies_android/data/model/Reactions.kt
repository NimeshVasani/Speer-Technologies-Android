package com.nimesh.vasani.speer_technologies_android.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Reactions(
    var likes: Int,
    var dislikes : Int
)
