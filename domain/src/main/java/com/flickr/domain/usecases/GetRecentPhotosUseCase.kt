package com.flickr.domain.usecases

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.gateways.ImagesGateway
import javax.inject.Inject

class GetRecentPhotosUseCase @Inject constructor(
  private val recentsGateway: ImagesGateway
) {
    suspend operator fun invoke(): RepositoryResult<List<AppPhoto>> {
        return recentsGateway.getRecents()
    }
}