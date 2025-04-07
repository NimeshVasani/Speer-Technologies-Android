package com.nimesh.vasani.speer_technologies_android.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import org.koin.androidx.compose.koinViewModel

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    usersViewmodel: UsersViewmodel = koinViewModel(),
) {

    val currentUser = usersViewmodel.currentUser.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier
            .fillMaxSize().safeDrawingPadding()
    ) {
        item {
            Box {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 20.dp, top = 10.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text =currentUser.value?.login?:"Unknown user",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.SansSerif,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = {  },
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(30.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AddBox,
                                contentDescription = "add",
                                modifier = Modifier.fillMaxSize(),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                        IconButton(
                            onClick = {  },
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(30.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "fav",
                                modifier = Modifier.fillMaxSize(),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                        IconButton(
                            onClick = {  },
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(30.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "setting",
                                modifier = Modifier.fillMaxSize(),
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }


                    // Profile picture and stats
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 16.dp, end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable(
                                    onClick = {},
                                    interactionSource = null,
                                    indication = null
                                ),

                            ) {

                            Image(
                                painter = rememberAsyncImagePainter(""),
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

                            Text(
                                text = "wjdwn",
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .border(
                                        BorderStroke(4.dp, MaterialTheme.colorScheme.primary),
                                        CircleShape
                                    )
                                    .padding(4.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.onPrimary),
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CustomText(
                                text = "vff",
                                fontSize = 16.sp
                            )
                            CustomText(text = "POSTS", color = Color.Gray, fontSize = 12.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CustomText(
                                text = "efef",
                                fontSize = 16.sp
                            )
                            CustomText(text = "Likes", color = Color.Gray, fontSize = 12.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CustomText(
                                text = "state.profileResponse?.user?.user_contacts?.toString() ?",
                                fontSize = 16.sp
                            )
                            CustomText(text = "CONTACTS", color = Color.Gray, fontSize = 12.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CustomText(
                                text =  "efee",
                                fontSize = 16.sp
                            )
                            CustomText(text = "AURA", color = Color.Gray, fontSize = 12.sp)
                        }
                    }


                    // Location and switch
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.PinDrop,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(20.dp)

                        )
                        Text(
                            text = "Exploring In Toronto",
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp),
                            fontWeight = ExtraBold,
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = false, onCheckedChange = {

                                //viewModel.updateUserExploreStatus(isExploreOn)
                            }, colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color.Blue,
                                uncheckedThumbColor = MaterialTheme.colorScheme.onPrimary,
                                uncheckedTrackColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                    }
                    Text(
                        text =  "No bio Available",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 20.dp)
                    )
                    //Edit Profile Nav route
                    Button(
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 16.dp, end = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Edit Profile",
                            fontSize = 16.sp,
                            fontWeight = ExtraBold,
                            fontFamily = FontFamily.SansSerif,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Text(
                        text = "Your Posts",
                        fontSize = 20.sp,
                        fontWeight = W600,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Divider(
                        thickness = 0.5.dp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    //this is for user own posts
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
