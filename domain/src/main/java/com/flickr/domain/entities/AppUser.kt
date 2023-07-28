package com.flickr.domain.entities

data class AppUser(
    val id: String,
    val nsid: String,
    val username: String?,
    val realname: String?,
    val photoCount: Int,
    val description: String,
    val iconUrl: String
)
