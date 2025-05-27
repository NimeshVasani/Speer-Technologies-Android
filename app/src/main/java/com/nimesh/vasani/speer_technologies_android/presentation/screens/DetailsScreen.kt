package com.nimesh.vasani.speer_technologies_android.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nimesh.vasani.speer_technologies_android.data.model.Posts

@Composable
fun DetailScreen(modifier: Modifier , post: Posts) {

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier.safeDrawingPadding()
    ) {
        Text(
            text = "Detail Screen",
            modifier = modifier
        )
        Text(
            text = post.title,
            modifier = modifier
        )
        Text(
            text = post.body,
            modifier = modifier
        )

        Text(
            text = "likes : ${post.reactions.likes}",
            modifier = modifier
        )
    }
}