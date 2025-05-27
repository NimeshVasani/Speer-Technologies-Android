package com.nimesh.vasani.speer_technologies_android.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.presentation.viewmodels.UsersViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    usersViewmodel: UsersViewmodel,
    onFollowersClick: (followers: List<User>) -> Unit,
    login: String
) {

    val uiState by usersViewmodel.uiState.collectAsState()


    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = {
            usersViewmodel.getFollowers(
                uiState.users.find { it.login == login }?.followers_url ?: "",
                currentUser = uiState.users.find { it.login == login }!!
            )

        },
        modifier = modifier,
        state = pullToRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = uiState.isLoading,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = pullToRefreshState
            )
        },
    ) {

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            item {
                Box {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = uiState.users.find { it.login == login }?.login ?: "",
                            fontSize = 30.sp,
                            fontWeight = ExtraBold,
                            fontFamily = FontFamily.SansSerif,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 20.dp, top = 10.dp, bottom = 16.dp),

                            )
                        // Profile picture and stats
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, bottom = 16.dp, end = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            Image(
                                painter = rememberAsyncImagePainter(uiState.users.find { it.login == login }?.avatar_url),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(90.dp)
                                    .border(
                                        BorderStroke(4.dp, MaterialTheme.colorScheme.primary),
                                        CircleShape
                                    )
                                    .padding(6.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )



                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CustomText(
                                    text = uiState.users.find { it.login == login }?.repos?.size?.toString()
                                        ?: "not visible",
                                    fontSize = 16.sp
                                )
                                CustomText(text = "Repos", color = Color.Gray, fontSize = 12.sp)
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable {
                                    onFollowersClick(
                                        uiState.users.find { it.login == login }?.followers
                                            ?: emptyList()
                                    )
                                }) {
                                CustomText(
                                    text = uiState.users.find { it.login == login }?.followers?.size.toString(),
                                    fontSize = 16.sp
                                )
                                CustomText(text = "Followers", color = Color.Gray, fontSize = 12.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CustomText(
                                    text = uiState.users.find { it.login == login }?.following?.size?.toString()
                                        ?: "not visible",
                                    fontSize = 16.sp
                                )
                                CustomText(
                                    text = "Followings",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }

                        }
                        Text(
                            text = "${uiState.users.find { it.login == login }?.login}'s Repositories",
                            fontSize = 20.sp,
                            fontWeight = W600,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        HorizontalDivider(
                            thickness = 0.5.dp
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                    }

                }
            }
            if (uiState.users.find { it.login == login }?.repos?.isNotEmpty() == true)
                itemsIndexed(uiState.users.find { it.login == login }?.repos!!) { index, item ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),


                        ) {
                        Column(
                            modifier = Modifier.padding(15.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = item.name,
                                fontSize = 20.sp,
                                fontWeight = W600,
                                modifier = Modifier
                            )
                            item.description?.let {
                                Text(
                                    text = it,
                                    fontSize = 18.sp,
                                    fontWeight = W600,
                                    modifier = Modifier
                                )
                            }
                            Text(
                                text = item.url,
                                fontSize = 16.sp,
                                fontWeight = W600,
                                modifier = Modifier
                            )
                        }
                    }
                }
            else {
                item {
                    Text(
                        "No repos found",
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = 2,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    style: TextStyle = TextStyle(),
    textAlign: TextAlign = TextAlign.Unspecified

) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = overflow,
        style = style,
        textAlign = textAlign

    )
}
