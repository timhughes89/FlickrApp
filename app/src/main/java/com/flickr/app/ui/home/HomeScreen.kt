package com.flickr.app.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flickr.app.R
import com.flickr.app.composables.InsetAwareCentredAlignedTopAppBar
import com.flickr.app.ui.FlickrDesignTokens
import com.flickr.domain.entities.AppPhoto
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
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

                        Text(text = "Flickr")
                    }
                }
            )
        }
    ) {
        state.value.photos?.let { photos ->
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
            ) {
                items(photos.size) {
                    GridItem(photo = photos[it])
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun GridItem(
    modifier: Modifier = Modifier,
    photo: AppPhoto
) {
    if (photo.tags.isNotBlank()) photo.tags.split(" ").toList()
//    val image = "https://live.staticflickr.com/{server-id}/{id}_{secret}_{size-suffix}.jpg"
    val image = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_w.jpg"

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(FlickrDesignTokens.token1)
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(FlickrDesignTokens.token1)
            )
    ) {

        GlideImage(
            imageModel = image,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomStart),
            text = photo.tags
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(FlickrDesignTokens.token1),
        ) {
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

            Text(text = photo.owner)
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun GridItemPreview() {
    GridItem(
        photo = AppPhoto(
            id = "",
            owner = "",
            secret = "",
            server = "",
            farm = 0,
            title = "Example",
            tags = "Nikon"
        )
    )
}