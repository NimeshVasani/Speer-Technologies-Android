package com.nimesh.vasani.speer_technologies_android.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun providesNetworkModule() = module {
    single{
        HttpClient() {
            install(HttpTimeout) {
                requestTimeoutMillis = 200_000
                connectTimeoutMillis = 200_000
                socketTimeoutMillis = 200_000
            }
            install(ContentNegotiation) {
                Json { ignoreUnknownKeys = true }
            }
        }
    }



}