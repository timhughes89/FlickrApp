package com.flickr.app.ui.photoDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flickr.app.R
import com.flickr.app.composables.CircularLoadingIndicator
import com.flickr.app.composables.TopAppBar
import com.flickr.app.ui.FlickrDesignTokens
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ImageDetailScreen(
    modifier: Modifier = Modifier,
    photoId: String,
    viewModel: PhotoDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.fetchImage(photoId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val topBarState = remember { mutableStateOf(true) }

        state.value.image?.let { appPhoto ->
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                ) {
                    GlideImage(
                        imageModel = appPhoto.imageUrl,
                        contentScale = ContentScale.Crop
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = appPhoto.title
                    )

                    Text(
                        text = "Taken: ${appPhoto.dates.taken}"
                    )
                }

                // Description
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary.copy(0.4f)),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier
                            .padding(FlickrDesignTokens.token1),
                        text = stringResource(R.string.label_description)
                    )

                    Text(
                        modifier = Modifier
                            .padding(FlickrDesignTokens.token1),
                        text = appPhoto.views
                    )
                }
            }
        }

        TopAppBar(
            title = { Text(text = "") },
            topBarState = topBarState,
            onBackPressed = {
                onBackPressed()
            }
        )

        CircularLoadingIndicator(loading = state.value.loading)
    }
}