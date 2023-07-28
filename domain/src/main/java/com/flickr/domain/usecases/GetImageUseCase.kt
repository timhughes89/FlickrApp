package com.flickr.domain.usecases

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppImage
import com.flickr.domain.gateways.ImagesGateway
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val recentsGateway: ImagesGateway
) {
    suspend operator fun invoke(imageId: String): RepositoryResult<AppImage> {
        return recentsGateway.getImageById(imageId)
    }
}