package com.flickr.data.repositories

import com.flickr.data.mapper.toAppPhoto
import com.flickr.data.services.FlickrService
import com.flickr.data.utils.SafeCallHelper
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.gateways.SearchGateway
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val flickrService: FlickrService
) : SearchGateway {

    override suspend fun getImagesBySearchTerm(searchTerm: String): RepositoryResult<List<AppPhoto>> {
        return SafeCallHelper.safeCall(Dispatchers.IO) {
            val request = flickrService.getPhotoBySearch(
                apiKey = "fde50a14c3fdefb33d4c2a3563764631",
                searchTerm = searchTerm,
                safeSearch = 1
            )

            request.photos.photos.map { it.toAppPhoto() }
        }
    }
}