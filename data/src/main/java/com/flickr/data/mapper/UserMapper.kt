package com.flickr.data.mapper

import com.flickr.data.utils.ImageUtils
import com.flickr.data.model.Person
import com.flickr.domain.entities.AppUser

fun Person.toAppUser(): AppUser {
    return AppUser(
        id = id,
        nsid = nsid,
        username = username?.name,
        realname = realname?.name,
        description = description.text,
        photoCount = photos.count.count,
        iconUrl = ImageUtils.buildIconUrl(iconfarm, iconserver, nsid)
    )
}