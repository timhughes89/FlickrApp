package com.flickr.data.repositories

import com.flickr.data.mapper.toAppPhoto
import com.flickr.data.mapper.toAppUser
import com.flickr.data.services.FlickrService
import com.flickr.data.utils.SafeCallHelper
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.entities.AppUser
import com.flickr.domain.gateways.UserGateway
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val flickrService: FlickrService
) : UserGateway {

    override suspend fun getUserProfile(userId: String): RepositoryResult<AppUser> {
        return SafeCallHelper.safeCall(Dispatchers.IO) {
            val request = flickrService.getUser(
                apiKey = "fde50a14c3fdefb33d4c2a3563764631",
                userId = userId,
                safeSearch = 1
            )

            val user = request.person.toAppUser()
            user
        }
    }

    override suspend fun getUserProfilePhotos(userId: String): RepositoryResult<List<AppPhoto>> {
        return SafeCallHelper.safeCall(Dispatchers.IO) {
            val request = flickrService.getUserPhotos(
                apiKey = "fde50a14c3fdefb33d4c2a3563764631",
                userId = userId,
                safeSearch = 1
            )

            val photos = request.photos.photos.map { it.toAppPhoto() }
            photos
        }
    }
}