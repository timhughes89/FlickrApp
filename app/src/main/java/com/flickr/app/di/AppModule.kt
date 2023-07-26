package com.flickr.app.di

import com.flickr.app.api.FlickrService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFlickrService() : FlickrService {
        return Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FlickrService::class.java)
    }
}