package com.flickr.app.ui.home

import androidx.lifecycle.viewModelScope
import com.flickr.app.common.BaseViewModel
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.entities.ErrorType
import com.flickr.domain.usecases.GetRecentPhotosUseCase
import com.flickr.domain.usecases.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
data class HomeViewState(
    val photos: List<AppPhoto>? = null,
    val loading: Boolean = true,
    val error: ErrorType? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecentPhotosUseCase: GetRecentPhotosUseCase,
    private val getSearchUseCase: GetSearchUseCase
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        fetchRecents()
    }

    /**
     * Defaulting to using the search endpoint as recents safe-search does not actually work.
     */
    private fun fetchRecents() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getSearchUseCase.invoke("Flowers")

            when (response) {
                is RepositoryResult.Success -> {
                    setState {
                        copy(
                            photos = response.value,
                            loading = false
                        )
                    }
                }
                else -> {

                }
            }
        }
    }

    suspend fun fetchItemsBySearch(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getSearchUseCase.invoke(searchTerm)

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