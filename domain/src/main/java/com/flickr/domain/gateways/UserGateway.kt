package com.flickr.domain.gateways

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.entities.AppUser

interface UserGateway {
    suspend fun getUserProfile(userId: String): RepositoryResult<AppUser>
    suspend fun getUserProfilePhotos(userId: String) : RepositoryResult<List<AppPhoto>>
}