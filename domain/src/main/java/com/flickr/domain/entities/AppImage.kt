package com.flickr.domain.entities

data class AppImage(
    val id: String,
    val secret: String,
    val dateuploaded: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val dates: AppPhotoDates,
    val views: String
)

data class AppPhotoDates(
    val posted: String,
    val taken: String
)