package com.flickr.data.mapper

import com.flickr.data.model.Photo
import com.flickr.domain.entities.AppPhoto

fun Photo.toAppPhoto(): AppPhoto {
    return AppPhoto(
        id = id,
        owner = owner,
        secret = secret,
        server = server,
        farm = farm,
        title = title,
        tags = tags
    )
}