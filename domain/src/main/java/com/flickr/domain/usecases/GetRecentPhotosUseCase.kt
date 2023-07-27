package com.flickr.domain.usecases

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.gateways.RecentsGateway
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRecentPhotosUseCase @Inject constructor(
  private val recentsGateway: RecentsGateway
) {
    suspend operator fun invoke(): RepositoryResult<List<AppPhoto>> {
        return recentsGateway.getRecents()
    }
}