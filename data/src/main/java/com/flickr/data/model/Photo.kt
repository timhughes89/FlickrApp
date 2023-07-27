package com.flickr.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosResponse(
    val photos: Photos,
    val stat: String
)

@JsonClass(generateAdapter = true)
data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int?,
    @Json(name = "photo")
    val photos: List<Photo>
)

@JsonClass(generateAdapter = true)
data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int?,
    val isFriend: Int?,
    val isFamily: Int?,
    val tags: String
)