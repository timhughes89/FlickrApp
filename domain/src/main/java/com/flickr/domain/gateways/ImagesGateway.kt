package com.flickr.domain.gateways

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppImage
import com.flickr.domain.entities.AppPhoto

interface ImagesGateway {
    suspend fun getRecents(): RepositoryResult<List<AppPhoto>>
    suspend fun getImageById(imageId: String): RepositoryResult<AppImage>
}