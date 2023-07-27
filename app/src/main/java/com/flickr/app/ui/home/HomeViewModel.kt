package com.flickr.app.ui.home

import androidx.lifecycle.viewModelScope
import com.flickr.app.common.BaseViewModel
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.usecases.GetRecentPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
data class HomeViewState(
    val photos: List<AppPhoto>? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecentPhotosUseCase: GetRecentPhotosUseCase
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        fetchRecents()
    }

    private fun fetchRecents() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRecentPhotosUseCase.invoke()

            when (response) {
                is RepositoryResult.Success -> {
                    setState {
                        copy(photos = response.value)
                    }
                }
                else -> {

                }
            }
        }
    }
}