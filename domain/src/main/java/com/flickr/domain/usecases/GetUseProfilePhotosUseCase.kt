package com.flickr.domain.usecases

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.gateways.UserGateway
import javax.inject.Inject

class GetUserProfilePhotosUseCase @Inject constructor(
    private val userGateway: UserGateway
) {
    suspend operator fun invoke(userId: String): RepositoryResult<List<AppPhoto>> {
        return userGateway.getUserProfilePhotos(userId)
    }
}