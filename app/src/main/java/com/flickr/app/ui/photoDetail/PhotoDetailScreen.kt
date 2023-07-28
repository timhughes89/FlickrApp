package com.flickr.app.ui.photoDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flickr.app.R
import com.flickr.app.composables.CircularLoadingIndicator
import com.flickr.app.composables.TopAppBar
import com.flickr.app.ui.FlickrDesignTokens
import com.flickr.app.ui.theme.AppTypography
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
                        .clip(RoundedCornerShape(0.dp, 0.dp, FlickrDesignTokens.token3, FlickrDesignTokens.token3))
                        .background(color = MaterialTheme.colorScheme.onBackground),
                ) {
                    GlideImage(
                        imageModel = appPhoto.imageUrl,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(FlickrDesignTokens.token4)
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = FlickrDesignTokens.token2),
                    text = appPhoto.title,
                    style = AppTypography.displayMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier
                        .height(FlickrDesignTokens.token4)
                )

                if (appPhoto.description.isNotBlank()) {
                    LinedRow(label = stringResource(id = R.string.label_description), value = appPhoto.description)
                }
                LinedRow(label = stringResource(id = R.string.label_date_taken), value = appPhoto.dates.taken)
                LinedRow(label = stringResource(id = R.string.label_date_uploaded), value = appPhoto.dateuploaded)
                LinedRow(label = stringResource(id = R.string.label_views), value = appPhoto.views)
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

@Composable
fun LinedRow(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(FlickrDesignTokens.token1),
                text = label,
                style = AppTypography.bodyLarge
            )

            Text(
                modifier = Modifier
                    .padding(FlickrDesignTokens.token1),
                text = value,
                style = AppTypography.bodyLarge
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}