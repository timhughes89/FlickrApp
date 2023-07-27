package com.flickr.domain.gateways

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import kotlinx.coroutines.flow.Flow

interface RecentsGateway {
    suspend fun getRecents(): RepositoryResult<List<AppPhoto>>
}