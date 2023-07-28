package com.flickr.app.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flickr.app.R
import com.flickr.app.composables.CircularLoadingIndicator
import com.flickr.app.composables.InsetAwareCentredAlignedTopAppBar
import com.flickr.app.ui.error.ErrorScreen
import com.flickr.app.ui.FlickrDesignTokens
import com.flickr.app.ui.theme.AppTypography
import com.flickr.domain.entities.AppPhoto
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onUserSelected: (String) -> Unit,
    onImageSelected: (String) -> Unit
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            InsetAwareCentredAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_flickr),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )

                        Spacer(
                            modifier = Modifier
                                .size(FlickrDesignTokens.token1)
                        )

                        Text(text = stringResource(R.string.label_flickr))
                    }
                }
            )
        }
    ) {

        if (state.value.error != null) {
            ErrorScreen(
                errorType = state.value.error!!,
                onRetry = {
                    viewModel.fetchImages()
                }
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                state.value.photos?.let { photos ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(FlickrDesignTokens.token1)
                    ) {
                        items(photos.size) {
                            ListItem(
                                photo = photos[it],
                                onImageSelected = { imageId ->
                                    onImageSelected(imageId)
                                },
                                onUserSelected = { user ->
                                    onUserSelected(user)
                                }
                            )
                        }
                    }
                }
            }
        }

        CircularLoadingIndicator(loading = state.value.loading)
    }
}

@ExperimentalMaterial3Api
@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    photo: AppPhoto,
    onImageSelected: (String) -> Unit,
    onUserSelected: (String) -> Unit
) {
    if (!photo.tags.isNullOrBlank()) photo.tags!!.split(" ").toList()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(FlickrDesignTokens.token1)
            .wrapContentHeight()
            .clip(RoundedCornerShape(FlickrDesignTokens.token1))
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(FlickrDesignTokens.token1)
            )
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.2f),
                ),
                shape = RoundedCornerShape(FlickrDesignTokens.token1)
            )
            .clickable {
                onImageSelected(photo.id)
            }
    ) {
        GlideImage(
            imageModel = photo.imageUrl,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            Column(
                modifier = Modifier
                    .clickable {
                        onUserSelected(photo.owner)
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(FlickrDesignTokens.token2),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // User image
                    Box(
                        modifier = Modifier
                            .size(FlickrDesignTokens.token4)
                            .background(Color.Black, CircleShape)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.onBackground.copy(0.4f),
                                shape = CircleShape
                            )
                    )

                    Spacer(
                        modifier = Modifier
                            .width(FlickrDesignTokens.token2)
                    )

                    Text(text = photo.owner)
                }

                if (!photo.tags.isNullOrEmpty()) {
                    Spacer(
                        modifier = Modifier
                            .height(FlickrDesignTokens.token1)
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = photo.tags!!,
                        style = AppTypography.labelMedium
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun ListItemPreview() {
    ListItem(
        photo = AppPhoto(
            id = "",
            owner = "",
            secret = "",
            imageUrl = "",
            title = "Example",
            tags = "Nikon"
        ),
        onUserSelected = {},
        onImageSelected = {}
    )
}