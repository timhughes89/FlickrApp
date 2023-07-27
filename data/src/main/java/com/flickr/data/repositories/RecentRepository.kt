package com.flickr.data.repositories

import com.flickr.data.mapper.toAppPhoto
import com.flickr.data.services.FlickrService
import com.flickr.data.utils.SafeCallHelper
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.gateways.RecentsGateway
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentRepository @Inject constructor(
    private val service: FlickrService
): RecentsGateway {

    private var photoList: List<AppPhoto>? = null

    override suspend fun getRecents(): RepositoryResult<List<AppPhoto>> {
        return if (photoList != null) RepositoryResult.Success(photoList!!)
        else SafeCallHelper.safeCall(Dispatchers.IO) {
            val recents = service.getRecents(
                apiKey = "fde50a14c3fdefb33d4c2a3563764631",
                format = "json",
                callback = 1,
                extras = "tags"
            )

            val photos = recents.photos.photos.map { photo -> photo.toAppPhoto() }
            photoList = photos
            photos
        }
    }
}