package com.flickr.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flickr.app.R
import com.flickr.app.composables.InsetAwareCentredAlignedTopAppBar
import com.flickr.app.model.Photo
import com.flickr.app.ui.FlickrDesignTokens

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
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(FlickrDesignTokens.token1)
        ) {
            state.value.photos?.let {
                items(it) { photo ->
                    GridItem(photo = photo)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun GridItem(
    modifier: Modifier = Modifier,
    photo: Photo
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(FlickrDesignTokens.token1)
            .height(120.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(FlickrDesignTokens.token1)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = photo.owner)
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun GridItemPreview() {
    GridItem(
        photo = Photo(
            id = "",
            owner = "",
            secret = "",
            server = "",
            farm = 0,
            title = "Example",
            isPublic = null,
            isFriend = null,
            isFamily = null
        )
    )
}