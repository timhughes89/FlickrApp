package com.flickr.domain.usecases

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.gateways.SearchGateway
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val searchGateway: SearchGateway
) {
    suspend operator fun invoke(searchTerm: String): RepositoryResult<List<AppPhoto>> {
        return searchGateway.getImagesBySearchTerm(searchTerm)
    }
}