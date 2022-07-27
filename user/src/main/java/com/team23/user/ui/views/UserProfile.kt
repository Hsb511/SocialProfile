package com.team23.user.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.user.R
import com.team23.user.domain.models.ContactData
import com.team23.user.domain.models.ContactDataCategory
import com.team23.user.ui.viewmodels.UserViewModel
import com.team23.user.ui.viewobjects.UserVO
import kotlinx.coroutines.launch

@Composable
fun UserProfile(userViewModel: UserViewModel, navigateToSearch: () -> Unit) {
    UserProfile(
        onBackToSearch = navigateToSearch,
        user = userViewModel.user.value,
        errorMessageResId = userViewModel.error.value
    )
}

@Composable
fun UserProfile(
    onBackToSearch: () -> Unit,
    user: UserVO?,
    errorMessageResId: Int = -1
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.user_profile_title)) },
                navigationIcon = {
                    IconButton(onClick = { onBackToSearch() }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 10.dp
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { Snackbar(it) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
                .padding(padding)
        ) {
            if (user == null && errorMessageResId == -1) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.secondary,
                        strokeWidth = 8.dp,
                        modifier = Modifier.size(80.dp)
                    )
                }
            } else if (errorMessageResId != -1) {
                val errorMessage = stringResource(id = errorMessageResId)
                LaunchedEffect(errorMessage) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(errorMessage)
                    }
                }
            } else if (user != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                ) {
                    Image(
                        painter = painterResource(id = user.backgroundResId),
                        contentDescription = "user profile background",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp, 32.dp, 0.dp, 42.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .height(IntrinsicSize.Min)
                        ) {
                            if (user.picture != null) {
                                Image(
                                    bitmap = user.picture.asImageBitmap(),
                                    contentDescription = "user picture",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, MaterialTheme.colors.background, CircleShape)
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.image_default_avatar),
                                    contentDescription = "default user picture",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, MaterialTheme.colors.background, CircleShape)
                                )
                            }
                            Icon(
                                painter = painterResource(user.genderResId),
                                contentDescription = "gender icon",
                                tint = MaterialTheme.colors.onBackground,
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.BottomEnd)
                            )
                        }
                        Text(
                            text = user.name,
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = user.dateOfBirth,
                            color = MaterialTheme.colors.onSurface,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.user_profile_contact_info).uppercase(),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.overline,
                    modifier = Modifier.padding(12.dp, 8.dp)
                )

                Divider()

                LazyColumn {
                    items(user.contactData) {
                        ContactDataView(contactData = it)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserProfilePreview() {
    UserProfile(
        onBackToSearch = {},
        user = UserVO(
            name = "Jesse Smith",
            picture = null,
            genderResId = R.drawable.ic_female,
            backgroundResId = R.drawable.picture_beach,
            dateOfBirth = "30/04/1996",
            contactData = listOf(
                ContactData(ContactDataCategory.EMAIL, "jesse@alpineskihouse.com"),
                ContactData(ContactDataCategory.PHONE, "+1 6175550123"),
                ContactData(
                    ContactDataCategory.ADDRESS,
                    "9614, SÃ¸ndermarksvej, \r\nKongsvinger \r\nNordjylland \r\nDenmark"
                )
            )
        )
    )
}