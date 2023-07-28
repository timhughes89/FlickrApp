package com.flickr.domain.entities

data class AppPhoto(
    val id: String,
    val owner: String,
    val secret: String,
    val imageUrl: String,
    val title: String,
    val tags: String?
)

