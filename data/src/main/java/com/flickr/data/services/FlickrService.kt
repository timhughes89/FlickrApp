package com.flickr.data.services

import com.flickr.data.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

//    https://www.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=fde50a14c3fdefb33d4c2a3563764631&format=json@nojsoncallback=1

    @GET("rest/?method=flickr.photos.getRecent")
    suspend fun getRecents(
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: Int,
        @Query("extras") extras: String
    ): PhotosResponse
}