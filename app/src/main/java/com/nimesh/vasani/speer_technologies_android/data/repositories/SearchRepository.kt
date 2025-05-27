package com.nimesh.vasani.speer_technologies_android.data.repositories

import com.nimesh.vasani.speer_technologies_android.data.model.PostResponse
import com.nimesh.vasani.speer_technologies_android.data.model.UserRepos
import com.nimesh.vasani.speer_technologies_android.others.Response
import com.nimesh.vasani.speer_technologies_android.others.Utils.POST_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class SearchRepository(
    var client : HttpClient
) {
    val json = Json { ignoreUnknownKeys = true }

    suspend fun getAllPosts() : Response<PostResponse> {
        try {
            val response: HttpResponse = client.get(POST_URL)

            if (response.status == HttpStatusCode.OK) {
                val result = response.bodyAsText()
                if (result.contains("error")) {
                    return Response.Error("Error: $result")
                }
                val postResponse = json.decodeFromString<PostResponse>(result)
                return Response.Success(postResponse)
            }

        } catch (e: Exception) {
            return Response.Error(e.localizedMessage ?: "Unknown Error")
        }
        return Response.Error("Unknown Error")
    }



}