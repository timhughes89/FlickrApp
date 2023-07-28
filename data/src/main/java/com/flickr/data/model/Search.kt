package com.flickr.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SearchResponse(
    val photos: Photos,
    val stat: String
)