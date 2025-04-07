package com.nimesh.vasani.speer_technologies_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nimesh.vasani.speer_technologies_android.presentation.navigation.AppNavigation
import com.nimesh.vasani.speer_technologies_android.presentation.theme.SpeerTechnologiesAndroidTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SpeerTechnologiesAndroidTheme {
                KoinAndroidContext {
                    AppNavigation() // pass to nav if needed
                }
            }
        }
    }

}
