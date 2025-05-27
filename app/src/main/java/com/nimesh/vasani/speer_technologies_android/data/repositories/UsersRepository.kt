package com.nimesh.vasani.speer_technologies_android.data.repositories


import android.util.Log
import com.nimesh.vasani.speer_technologies_android.data.model.GitHubUserResponse
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.data.model.UserRepos
import com.nimesh.vasani.speer_technologies_android.others.Response
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class UsersRepository(
    private val client: HttpClient,

    ) {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun searchGitHubUsers(query: String): Response<GitHubUserResponse> {

        try {
            val response: HttpResponse = client.get("https://api.github.com/search/users") {
                parameter("q", query)

            }

            if (response.status == HttpStatusCode.OK) {
                val result = response.bodyAsText()
                if (result.contains("error")) {
                    return Response.Error("Error: $result")
                }
                val gitHubUserResponse = json.decodeFromString<GitHubUserResponse>(result)
                return Response.Success(gitHubUserResponse)
            }

        } catch (e: Exception) {
            return Response.Error(e.localizedMessage ?: "Unknown Error")
        }
        return Response.Error("Unknown Error")
    }

    suspend fun getFollowersFollowing(link: String): Response<List<User>> {

        try {
            val response: HttpResponse = client.get(link)
            Log.e("UsersRepository", "Error: $")

            if (response.status == HttpStatusCode.OK) {
                val result = response.bodyAsText()
                if (result.contains("error")) {
                    return Response.Error("Error: $result")
                    Log.e("UsersRepository", "Error: $result")
                }
                val gitFollowers = json.decodeFromString<List<User>>(result)
                return Response.Success(gitFollowers)
            }

        } catch (e: Exception) {

            return Response.Error(e.localizedMessage ?: "Unknown Error")
        }
        return Response.Error("Unknown Error")
    }

    suspend fun getRepos(link: String): Response<List<UserRepos>> {

        try {
            val response: HttpResponse = client.get(link)

            if (response.status == HttpStatusCode.OK) {
                val result = response.bodyAsText()
                if (result.contains("error")) {
                    return Response.Error("Error: $result")
                }
                val gitFollowers = json.decodeFromString<List<UserRepos>>(result)
                return Response.Success(gitFollowers)
            }

        } catch (e: Exception) {
            return Response.Error(e.localizedMessage ?: "Unknown Error")
        }
        return Response.Error("Unknown Error")
    }


}
