package com.flickr.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val person: Person,
    val stat: String
)

@JsonClass(generateAdapter = true)
data class Person(
    val id: String,
    val nsid: String,
    val username: Username?,
    val realname: Realname?,
    val iconserver: String,
    val iconfarm: Int,
    val photos: UserPhotos,
    val description: Description
)

@JsonClass(generateAdapter = true)
data class Description(
    @Json(name = "_content")
    val text: String
)

@JsonClass(generateAdapter = true)
data class Realname(
    @Json(name = "_content")
    val name: String
)

@JsonClass(generateAdapter = true)
data class Username(
    @Json(name = "_content")
    val name: String
)

@JsonClass(generateAdapter = true)
data class UserPhotos(
    val count: PhotoCount
)

@JsonClass(generateAdapter = true)
data class PhotoCount(
    @Json(name = "_content")
    val count: Int
)