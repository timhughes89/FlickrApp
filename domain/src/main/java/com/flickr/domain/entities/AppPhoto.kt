package com.flickr.domain.entities

data class AppPhoto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val tags: String
)

