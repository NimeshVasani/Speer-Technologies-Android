package com.nimesh.vasani.speer_technologies_android.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.nimesh.vasani.speer_technologies_android.R
import com.nimesh.vasani.speer_technologies_android.data.model.User

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FollowersScreen(
    modifier: Modifier = Modifier,
    followers: List<User> = emptyList(),
    onFollowersClick: (user:User) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        stickyHeader {
            Text(
                text = "followers", modifier = Modifier, fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        items(followers.size) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp).clickable {
                        onFollowersClick(followers[it])
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(followers[it].avatar_url),
                    contentDescription = "user profile pic",
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                        .padding(4.dp)
                        .clip(CircleShape)
                        .size(30.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = followers[it].login,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


            }
        }
    }
}