package com.nimesh.vasani.speer_technologies_android.data.repositories



import com.nimesh.vasani.speer_technologies_android.data.model.GitHubUserResponse
import com.nimesh.vasani.speer_technologies_android.others.Response
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class AuthRepository(
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
    }

}
