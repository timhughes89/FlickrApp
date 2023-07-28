package com.flickr.data.mapper

import com.flickr.data.model.Image
import com.flickr.data.model.Photo
import com.flickr.data.utils.ImageUtils
import com.flickr.domain.entities.AppImage
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.entities.AppPhotoDates

fun Photo.toAppPhoto(): AppPhoto {
    return AppPhoto(
        id = id,
        owner = owner,
        secret = secret,
        imageUrl = ImageUtils.buildImageUrl(server, id, secret),
        title = title,
        tags = tags
    )
}

fun Image.toAppImage(): AppImage {
    return AppImage(
        id = id,
        secret = secret,
        dateuploaded = dateuploaded,
        title = title.content,
        description = description.text,
        dates = AppPhotoDates(
            posted = dates.posted,
            taken = dates.taken
        ),
        imageUrl = ImageUtils.buildImageUrl(server, id, secret),
        views = views
    )
}