package com.nimesh.vasani.speer_technologies_android.presentation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.nimesh.vasani.speer_technologies_android.R
import kotlin.math.roundToInt

@Composable
fun ProfileScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition()
    var imageOffsetX by remember { mutableFloatStateOf(0f) }

    // Infinite horizontal scroll animation
    val infiniteScroll by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(infiniteScroll) {
        imageOffsetX = infiniteScroll * 1000f
    }

    val screenWidth = remember { mutableStateOf(200.dp) }
    val density = LocalDensity.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black
            )
            .onGloballyPositioned {
                screenWidth.value = with(density) { it.size.width.toDp() }
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(R.drawable.sign_up_back)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .graphicsLayer {
                    translationX = imageOffsetX
                        .roundToInt()
                        .toFloat()
                    transformOrigin = TransformOrigin.Center
                    scaleX = 3f
                    scaleY = 1f
                }

        )
        Text(
            text = "Register",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = Color.White.copy(0.72f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 20.dp)
                .clickable {
                    onSignUpClick()
                }
        )
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .wrapContentSize()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Speer Technologies",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                maxLines = 1,
                softWrap = false,
                fontSize = when {
                    screenWidth.value < 500.dp -> 34.sp
                    screenWidth.value < 700.dp -> 40.sp
                    else -> 48.sp
                }, style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 10f,
                        offset = Offset(
                            x = 0f,
                            y = 0f
                        )
                    )
                ),
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "This is android assessment",
                modifier = Modifier
                    .widthIn(max = with(LocalDensity.current) { 700.dp })
                    .padding(horizontal = 20.dp),
                textAlign = TextAlign.Center,
                color = Color.White.copy(0.82f),
                maxLines = 2,
                fontSize = when {
                    screenWidth.value < 500.dp -> 16.sp
                    screenWidth.value < 600.dp -> 18.sp
                    screenWidth.value < 700.dp -> 24.sp
                    else -> 28.sp
                },
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 10f,
                        offset = Offset(
                            x = 0f,
                            y = 0f
                        )
                    )
                )
            )

            ElevatedButton(
                onClick = {
                    onLoginClick()
                },
                modifier = Modifier
                    .widthIn(max = with(LocalDensity.current) { 700.dp })
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFF0B6623),
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_apple),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(
                        18.dp
                            .plus(
                                when {
                                    screenWidth.value < 500.dp -> 2.dp
                                    screenWidth.value < 700.dp -> 4.dp
                                    else -> 6.dp
                                }
                            )
                    )

                )
                Text(
                    text = buildAnnotatedString {
                        append("Login with ")
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Id-Password")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp.plus(
                                when {
                                    screenWidth.value < 500.dp -> 2.dp
                                    screenWidth.value < 700.dp -> 4.dp
                                    else -> 6.dp
                                }
                            )
                        ),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = when {
                        screenWidth.value < 500.dp -> 18.sp
                        screenWidth.value < 700.dp -> 24.sp
                        else -> 32.sp
                    },
                    fontWeight = FontWeight.Normal
                )
            }
            ElevatedButton(
                onClick = {},
                modifier = Modifier
                    .widthIn(max = with(LocalDensity.current) { 700.dp })
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(
                        18.dp
                            .plus(
                                when {
                                    screenWidth.value < 500.dp -> 2.dp
                                    screenWidth.value < 700.dp -> 4.dp
                                    else -> 6.dp
                                }
                            )
                    )

                )
                Text(
                    text = buildAnnotatedString {
                        append("Sign in with ")
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Google")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp.plus(
                                when {
                                    screenWidth.value < 500.dp -> 2.dp
                                    screenWidth.value < 700.dp -> 4.dp
                                    else -> 6.dp
                                }
                            )
                        ),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = when {
                        screenWidth.value < 500.dp -> 18.sp
                        screenWidth.value < 700.dp -> 24.sp
                        else -> 32.sp
                    },
                    fontWeight = FontWeight.Normal
                )
            }
            ElevatedButton(
                onClick = {},
                modifier = Modifier
                    .widthIn(max = with(LocalDensity.current) { 700.dp })
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4873d6)
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_facebook),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(
                        18.dp
                            .plus(
                                when {
                                    screenWidth.value < 500.dp -> 2.dp
                                    screenWidth.value < 700.dp -> 4.dp
                                    else -> 6.dp
                                }
                            )
                    )
                )
                Text(
                    text = buildAnnotatedString {
                        append("Sign in with ")
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                        append("Facebook")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp.plus(
                                when {
                                    screenWidth.value < 500.dp -> 2.dp
                                    screenWidth.value < 700.dp -> 4.dp
                                    else -> 6.dp
                                }
                            )
                        ),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = when {
                        screenWidth.value < 500.dp -> 18.sp
                        screenWidth.value < 700.dp -> 24.sp
                        else -> 28.sp
                    },
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Terms of use",
                    color = Color.White.copy(0.72f),
                    fontSize = when {
                        screenWidth.value < 500.dp -> 14.sp
                        screenWidth.value < 700.dp -> 18.sp
                        else -> 22.sp
                    },
                )
                Spacer(modifier = Modifier.width(10.dp))
                VerticalDivider(
                    thickness = 1.dp,
                    color = Color.White.copy(0.72f),
                    modifier = Modifier.height(10.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Privacy policy",
                    color = Color.White.copy(0.72f),
                    fontSize = when {
                        screenWidth.value < 500.dp -> 14.sp
                        screenWidth.value < 700.dp -> 18.sp
                        else -> 22.sp
                    },
                )
            }
        }
    }

}
