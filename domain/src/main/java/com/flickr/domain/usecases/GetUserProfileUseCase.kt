package com.flickr.domain.usecases

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppUser
import com.flickr.domain.gateways.UserGateway
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userGateway: UserGateway
) {
    suspend operator fun invoke(userId: String): RepositoryResult<AppUser> {
        return userGateway.getUserProfile(userId)
    }
}