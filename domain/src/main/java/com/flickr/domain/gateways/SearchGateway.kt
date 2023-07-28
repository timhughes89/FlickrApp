package com.flickr.domain.gateways

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto

interface SearchGateway {
    suspend fun getImagesBySearchTerm(searchTerm: String): RepositoryResult<List<AppPhoto>>
}