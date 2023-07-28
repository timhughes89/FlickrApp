package com.flickr.app.di

import android.content.Context
import android.net.ConnectivityManager
import com.flickr.data.repositories.RecentRepository
import com.flickr.data.repositories.SearchRepository
import com.flickr.data.repositories.UserRepository
import com.flickr.data.services.FlickrService
import com.flickr.domain.gateways.ImagesGateway
import com.flickr.domain.gateways.SearchGateway
import com.flickr.domain.gateways.UserGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFlickrService(): FlickrService {
        return Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FlickrService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecentsGateway(
        recentsRepository: RecentRepository
    ): ImagesGateway {
        return recentsRepository
    }

    @Provides
    @Singleton
    fun provideUserGateway(
        userRepository: UserRepository
    ): UserGateway {
        return userRepository
    }

    @Provides
    @Singleton
    fun provideSearchGateway(
        searchRepository: SearchRepository
    ): SearchGateway {
        return searchRepository
    }

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}