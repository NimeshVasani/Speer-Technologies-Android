package com.nimesh.vasani.speer_technologies_android.data.repositories



import android.util.Log
import androidx.compose.foundation.text.selection.DisableSelection
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json

class UsersRepository(
    private val client: HttpClient,

) {
    private val json = Json { ignoreUnknownKeys = true }
    suspend fun searchGitHubUsers(query: String): Flow<Response<GitHubUserResponse>> = flow {

        try {
            val response: HttpResponse = client.get("https://api.github.com/search/users") {
                parameter("q", query)

            }

            if (response.status == HttpStatusCode.OK) {
                val result = response.bodyAsText()
                if (result.contains("error")) {
                    emit(Response.Error("Error: $result"))
                }
                val gitHubUserResponse = json.decodeFromString<GitHubUserResponse>(result)
                emit(Response.Success(gitHubUserResponse))
            }

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO )

    suspend fun getFollowersFollowing(link: String): Flow<Response<List<User>>> = flow {

        try {
            val response: HttpResponse = client.get(link)
            Log.e("UsersRepository", "Error: $")

            if (response.status == HttpStatusCode.OK) {
                val result = response.bodyAsText()
                if (result.contains("error")) {
                    emit(Response.Error("Error: $result"))
                    Log.e("UsersRepository", "Error: $result")
                }
                val gitFollowers = json.decodeFromString<List<User>>(result)
                emit(Response.Success(gitFollowers))
            }

        } catch (e: Exception) {

            emit(Response.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    suspend fun getRepos(link: String): Flow<Response<List<UserRepos>>> = flow {

        try {
            val response: HttpResponse = client.get(link)

            if (response.status == HttpStatusCode.OK) {
                val result = response.bodyAsText()
                if (result.contains("error")) {
                    emit(Response.Error("Error: $result"))
                }
                val gitFollowers = json.decodeFromString<List<UserRepos>>(result)
                emit(Response.Success(gitFollowers))
            }

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO )



}
