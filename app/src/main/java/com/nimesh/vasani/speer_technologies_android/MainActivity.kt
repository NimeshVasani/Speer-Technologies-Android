package com.nimesh.vasani.speer_technologies_android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nimesh.vasani.speer_technologies_android.presentation.navigation.AppNavigation
import com.nimesh.vasani.speer_technologies_android.presentation.theme.SpeerTechnologiesAndroidTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {

    private var oauthCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        handleGitHubRedirect(intent?.data)

        setContent {
            SpeerTechnologiesAndroidTheme {
                KoinAndroidContext {
                    AppNavigation(oauthCode) // pass to nav if needed
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleGitHubRedirect(intent.data)
    }


    private fun handleGitHubRedirect(uri: Uri?) {
        if (uri != null && uri.toString().startsWith("https://www.nmvasani.com")) {
            oauthCode = uri.getQueryParameter("code")
            // Ideally: use a ViewModel or rememberSaveable state to act on this code
        }
    }
}
