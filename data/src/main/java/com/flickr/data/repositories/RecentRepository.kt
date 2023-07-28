package com.flickr.data.repositories

import com.flickr.data.mapper.toAppImage
import com.flickr.data.mapper.toAppPhoto
import com.flickr.data.services.FlickrService
import com.flickr.data.utils.SafeCallHelper
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppImage
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.gateways.ImagesGateway
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentRepository @Inject constructor(
    private val service: FlickrService
): ImagesGateway {

    private var recentImagesList: List<AppPhoto>? = null

    override suspend fun getRecents(): RepositoryResult<List<AppPhoto>> {
        return if (recentImagesList != null) RepositoryResult.Success(recentImagesList!!)
        else SafeCallHelper.safeCall(Dispatchers.IO) {
            val recents = service.getRecents(
                apiKey = "fde50a14c3fdefb33d4c2a3563764631",
                format = "json",
                callback = 1,
                extras = "tags",
                safeSearch = 1
            )

            val photos = recents.photos.photos.map { photo -> photo.toAppPhoto() }
            recentImagesList = photos
            photos
        }
    }

    override suspend fun getImageById(imageId: String): RepositoryResult<AppImage> {
        return SafeCallHelper.safeCall(Dispatchers.IO) {
            val image = service.getPhotoInfo(
                apiKey = "fde50a14c3fdefb33d4c2a3563764631",
                photoId = imageId,
                format = "json",
                callback = 1,
            )

            image.photo.toAppImage()
        }
    }
}