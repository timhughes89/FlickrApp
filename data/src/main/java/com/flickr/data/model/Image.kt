package com.flickr.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageResponse(
    val photo: Image,
    val stat: String
)

@JsonClass(generateAdapter = true)
data class Image(
    val id: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val dateuploaded: String,
    val title: Title,
    val dates: Dates,
    val views: String
)

@JsonClass(generateAdapter = true)
data class Title(
    @Json(name = "_content")
    val content: String
)

@JsonClass(generateAdapter = true)
data class Dates(
    val posted: String,
    val taken: String,
)