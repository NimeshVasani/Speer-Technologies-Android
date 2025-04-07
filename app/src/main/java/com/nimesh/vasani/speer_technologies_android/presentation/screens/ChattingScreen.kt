package com.nimesh.vasani.speer_technologies_android.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.nimesh.vasani.speer_technologies_android.R


@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun ChattingScreen(modifier: Modifier = Modifier) {
    val messages = remember {
        mutableStateListOf(
            "Hello? ejeh efhehfuef efhe fefh fefewhf ehf fuwfwhfwe",
            "Hey",
            "How are you?",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
            "I am Good",
        )
    }
    var screenWith by remember { mutableStateOf(400.dp) }
    var listState = rememberLazyListState(messages.size - 1)
    val density = LocalDensity.current
    var query by remember { mutableStateOf("") }
    val localFocusManager = LocalFocusManager.current
    LaunchedEffect(messages.size) {

        // Scroll to the top of the item
        listState.animateScrollToItem(messages.size - 1, scrollOffset = -20)
    }

    Box(
        modifier = Modifier.fillMaxSize().imePadding()
    ) {
        //write your condition to check if no messages
        if (1 == 2) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Image(
                    painter = painterResource(R.drawable.ic_apple),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(50.dp)
                        .height(50.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = " navUiState.aiName",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "navUiState.aiDescription",
                    fontWeight = FontWeight.Thin,
                    color = Color.Black.copy(0.82f),
                    fontFamily = FontFamily.Default,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "navUiState.extraInfo",
                    fontWeight = FontWeight.Thin,
                    color = Color.Black.copy(0.85f),
                    fontFamily = FontFamily.Default,
                    fontSize = 18.sp,
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        } else
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .background(
                        Color(0xFFFAF9F6)
                    )
                    .onGloballyPositioned {
                        screenWith = with(density) { it.size.width.toDp() }
                    }
                    .padding(top = 100.dp, bottom = 80.dp)
                   ,
            ) {

                items(messages.size) { index ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = messages.elementAt(index),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier
                                .align(if (index % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd)
                                .wrapContentWidth()
                                .padding(10.dp)
                                .clip(RoundedCornerShape(30))
                                .background(
                                    color = if (index % 2 == 0) Color(0XFFFAD5A5) else Color(
                                        0XFFAFE1AF
                                    )
                                )
                                .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
                                .widthIn(max = screenWith / 1.5f)
                        )
                    }
                }
            }

        Card(

            colors = CardDefaults.cardColors(
                containerColor = Color(0XFFE6E6FA)
            ),
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier

                    .padding(top = 50.dp, start = 10.dp, end = 10.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBackIos,
                    contentDescription = null,
                )
                Text(
                    text = "Nimesh Vasani",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .weight(1f)
                        .wrapContentWidth()
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .zIndex(10f)
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .imePadding(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = Color.Black.copy(0.3f),
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            BasicTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(0.3f))
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
                    .padding(10.dp),
                value = query,

                onValueChange = {
                    query = it
                },
                cursorBrush = SolidColor(Color.Black),
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black.copy(alpha = 0.92f),
                    fontSize = 14.sp
                ),
                maxLines = 4,
                decorationBox = { innerTextField ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.weight(1f)) {
                            if (query.isEmpty()) Text(
                                "Type your message",
                                style = LocalTextStyle.current.copy(
                                    color = Color.Black.copy(alpha = 0.4f),
                                    fontSize = 14.sp
                                )
                            )
                            innerTextField()
                        }
                        AnimatedVisibility(
                            visible = query.isNotBlank(),
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(
                                onClick = {
                                    // Handle send action here
                                    messages.add(query)
                                    // Handle loading, success, and error states
                                    query = "" // Clear text after sending
                                },
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Color.White.copy(0.1f)
                                    )
                                    .width(30.dp)
                                    .height(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowUpward,
                                    tint = Color.White,
                                    contentDescription = "Send",
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}