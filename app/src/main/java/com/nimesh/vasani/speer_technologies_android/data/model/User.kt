package com.nimesh.vasani.speer_technologies_android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val avatar_url: String? = null,
    val username: String? = null,
    val followers_url :String? = null,
    val following_url:String? = null,
    val gists_url:String? = null,
    val repos_url:String? = null,
    val url:String? = null,
    var followers: List<User>? = null,
    var following: List<User>? = null,
    var gists: List<User>? = null,
    var repos: List<UserRepos>? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

