package com.flickr.app.ui.error

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flickr.app.R
import com.flickr.app.ui.FlickrDesignTokens
import com.flickr.domain.entities.ErrorType

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorType: ErrorType,
    onRetry: () -> Unit
) {
  Column(
      modifier = modifier
          .fillMaxSize()
          .padding(FlickrDesignTokens.token6),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
  ) {

      Box(
          modifier = Modifier
              .size(FlickrDesignTokens.token7)
              .background(
                  color = MaterialTheme.colorScheme.primary.copy(0.4f),
                  shape = RoundedCornerShape(50)
              )
              .border(
                  border = BorderStroke(
                      width = 2.dp,
                      color = MaterialTheme.colorScheme.primary
                  ),
                  shape = RoundedCornerShape(50)
              )
      ) {
          Icon(
              modifier = Modifier
                  .align(Alignment.Center),
              painter = painterResource(id = R.drawable.ic_error),
              tint = MaterialTheme.colorScheme.primary,
              contentDescription = null
          )
      }

      Spacer(
          modifier = Modifier
              .padding(FlickrDesignTokens.token1)
      )

      Text(
          text = errorType.title,
          textAlign = TextAlign.Center
      )

      if (errorType == ErrorType.GenericError) {
          Button(onClick = { onRetry() }) {
              Text(text = stringResource(id = R.string.label_retry))
          }
      }
  }
}