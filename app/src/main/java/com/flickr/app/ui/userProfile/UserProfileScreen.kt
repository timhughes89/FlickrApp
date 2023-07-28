package com.flickr.app.ui.userProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.flickr.app.R
import com.flickr.app.composables.CircularLoadingIndicator
import com.flickr.app.composables.TopAppBar
import com.flickr.app.ui.FlickrDesignTokens
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterial3Api
@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    userId: String,
    viewModel: UserProfileViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.fetchUserInfo(userId)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopAppBar(
                title = { },
                topBarState = remember { mutableStateOf(true) },
                onBackPressed = {
                    onBackPressed()
                }
            )

            state.value.user?.let { user ->
                Box(
                    modifier = Modifier
                        .size(FlickrDesignTokens.token9)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        ),
                ) {
                    GlideImage(
                        modifier = Modifier
                            .size(FlickrDesignTokens.token7),
                        imageModel = user.iconUrl,
                        contentScale = ContentScale.FillBounds
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(FlickrDesignTokens.token2)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = user.realname ?: user.id,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier
                            .height(FlickrDesignTokens.tokenHalf)
                    )

                    Text(
                        text = user.username ?: user.id,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(
                        modifier = Modifier
                            .height(FlickrDesignTokens.token2)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "${user.photoCount}",
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = stringResource(R.string.label_photos),
                            color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(FlickrDesignTokens.token2)
                    )

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = FlickrDesignTokens.token4)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(FlickrDesignTokens.token1)
                    )
                }
            }

            state.value.userPhotos?.let { photoList ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(FlickrDesignTokens.tokenHalf)
                ) {
                    items(photoList.size) { index ->
                        Box(
                            modifier = Modifier
                                .aspectRatio(1.1f)
                                .padding(FlickrDesignTokens.tokenHalf)
                                .background(MaterialTheme.colorScheme.onBackground)
                        ) {
                            GlideImage(
                                imageModel = photoList[index].imageUrl,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

        CircularLoadingIndicator(loading = state.value.loading)
    }
}