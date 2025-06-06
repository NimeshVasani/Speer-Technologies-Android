package com.nimesh.vasani.speer_technologies_android.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import com.nimesh.vasani.speer_technologies_android.data.model.User
import com.nimesh.vasani.speer_technologies_android.presentation.viewmodels.UsersViewmodel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    onUsersClick: (user: User) -> Unit,
    usersViewModel: UsersViewmodel
) {

    val queryText = remember { mutableStateOf("") }
    val searchQuery = remember { mutableStateOf("") }

    val searchActive = remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current

    val uiState = usersViewModel.uiState.collectAsStateWithLifecycle()


    LaunchedEffect(queryText.value) {
        // Wait for a short delay to prevent calling on each keystroke
        delay(500)
        if (queryText.value != searchQuery.value) {
            searchQuery.value = queryText.value
            if (searchQuery.value.isNotBlank()) {
                usersViewModel.searchUsers(searchQuery.value)
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        stickyHeader {
            Column(verticalArrangement = Arrangement.Top, modifier = Modifier.background(Color(0XFFFAF9F6))) {

                val colors1 = SearchBarDefaults.colors(containerColor = Color.White)
                Text(
                    "Speer Technologies Android",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                SearchBar(
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = queryText.value,
                            onQueryChange = {
                                if (!it.startsWith(" ") && !it.endsWith("  ")) {
                                    queryText.value = it
                                }

                            },
                            onSearch = {

                            },
                            expanded = false,
                            onExpandedChange = { searchActive.value = it },
                            enabled = true,
                            placeholder = {
                                Text(
                                    text = "Search Users by name", color = Color.Black
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search, contentDescription = ""
                                )
                            },
                            trailingIcon = {
                                if (queryText.value.isNotEmpty()) IconButton(onClick = {
                                    queryText.value = ""
                                    localFocusManager.clearFocus()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            },
                            interactionSource = null,
                        )
                    },
                    expanded = false,
                    onExpandedChange = searchActive.value::equals,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(size = 10.dp),
                    colors = colors1,
                    content = {}
                )
                Spacer(modifier = Modifier.height(20.dp))

            }
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (uiState.value?.users?.isNotEmpty() == true) {
            val users = uiState.value.users
            items(users.size) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(users[it].avatar_url),
                        contentDescription = "user profile pic",
                        modifier = Modifier
                            .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .size(80.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 10.dp)
                            .height(80.dp)
                            .clickable {

                                onUsersClick(users[it])
                            },
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = users[it].login,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "1",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            color = Color.Black.copy(0.72f),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        } else {
            item {
                Text(
                    "No recent searches",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}