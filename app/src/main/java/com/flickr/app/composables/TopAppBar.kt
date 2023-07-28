package com.flickr.app.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.flickr.app.ui.FlickrDesignTokens
import com.flickr.app.ui.theme.AppTypography

@Composable
fun InsetAwareCentredAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    divider: Boolean = true
) {

    Column(
        modifier = modifier
            .background(
                backgroundColor
            )
    ) {
        CenterAlignedTopAppBar(
            title = title,
            modifier = Modifier
                .statusBarsPadding(),
            navigationIcon = navigationIcon,
            actions = actions,
            colors = colors,
            scrollBehavior = scrollBehavior
        )

        if (divider) {
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.10F),
                thickness = 1.dp
            )
        }
    }
}

@Composable
internal fun TitleTopBar(
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = FlickrDesignTokens.token3, vertical = FlickrDesignTokens.token1)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f, true),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Reduce",
                style = AppTypography.displaySmall,
                color = contentColor
            )
            Text(
                text = "Cut your emissions by shopping sustainably",
                color = contentColor
            )
        }

        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                tint = contentColor,
                contentDescription = null
            )
        }
    }
}

/**
 * A composable that creates the TopAppBar.
 * This is created outside of the scaffold as to be able to be shown on top of
 * the table content.
 */
@Composable
internal fun TopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    icon: ImageVector? = null,
    topBarState: MutableState<Boolean>,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = topBarState.value,
            enter = slideInVertically(
                animationSpec = tween(500),
                initialOffsetY = { -it }
            ),
            exit = slideOutVertically(
                animationSpec = tween(500),
                targetOffsetY = { -it }
            ),
        ) {
            Row(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .height(FlickrDesignTokens.token7),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(FlickrDesignTokens.token1)
                        .background(Color.White, CircleShape),
                    onClick = onBackPressed
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardBackspace,
                        tint = contentColor,
                        contentDescription = null,
                    )
                }

                title()

                IconButton(
                    modifier = Modifier
                        .padding(FlickrDesignTokens.token1),
                    onClick = { }
                ) {
                    icon?.let {
                        Icon(
                            imageVector = it,
                            tint = contentColor,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}