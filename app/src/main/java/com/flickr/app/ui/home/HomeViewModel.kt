package com.flickr.app.ui.home


import androidx.lifecycle.viewModelScope
import com.flickr.app.common.BaseViewModel
import com.flickr.app.api.FlickrService
import com.flickr.app.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
data class HomeViewState(
    val photos: List<Photo>? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: FlickrService
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        fetchRecents()
    }

    private fun fetchRecents() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.getRecents(
                apiKey = "fde50a14c3fdefb33d4c2a3563764631",
                format = "json",
                callback = 1
            )

            setState {
                copy(photos = response.photos.photos)
            }
        }
    }
}