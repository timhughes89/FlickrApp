package com.flickr.data.services

import com.flickr.data.model.ImageResponse
import com.flickr.data.model.PhotosResponse
import com.flickr.data.model.SearchResponse
import com.flickr.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

//    https://www.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=fde50a14c3fdefb33d4c2a3563764631&format=json@nojsoncallback=1

    @GET("rest/?method=flickr.photos.getRecent")
    suspend fun getRecents(
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: Int,
        @Query("extras") extras: String,
        @Query("safe_search") safeSearch: Int
    ): PhotosResponse

//    https://www.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=fde50a14c3fdefb33d4c2a3563764631&photo_id=53071730362&format=json&nojsoncallback=1

    @GET("rest/?method=flickr.photos.getInfo")
    suspend fun getPhotoInfo(
        @Query("api_key") apiKey: String,
        @Query("photo_id") photoId: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: Int
    ) : ImageResponse

//    https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=fde50a14c3fdefb33d4c2a3563764631&text=Dog&format=json&nojsoncallback=1
    @GET("rest/?method=flickr.photos.search")
    suspend fun getPhotoBySearch(
        @Query("api_key") apiKey: String,
        @Query("text") searchTerm: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: Int,
        @Query("safe_search") safeSearch: Int
    ) : SearchResponse

    //    rest/?method=flickr.people.getInfo&api_key=fde50a14c3fdefb33d4c2a3563764631&user_id=97102756%40N07&format=json&nojsoncallback=1
    @GET("rest/?method=flickr.people.getInfo")
    suspend fun getUser(
        @Query("api_key") apiKey: String,
        @Query("user_id") userId: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: Int,
        @Query("safe_search") safeSearch: Int
    ) : UserResponse

    @GET("rest/?method=flickr.people.getPhotos")
    suspend fun getUserPhotos(
        @Query("api_key") apiKey: String,
        @Query("user_id") userId: String,
        @Query("format") format: String,
        @Query("nojsoncallback") callback: Int,
        @Query("safe_search") safeSearch: Int
    ): PhotosResponse

}