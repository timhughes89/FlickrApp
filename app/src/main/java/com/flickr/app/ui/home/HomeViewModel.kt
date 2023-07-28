package com.flickr.app.ui.home

import androidx.lifecycle.viewModelScope
import com.flickr.app.NetworkManager
import com.flickr.app.common.BaseViewModel
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.entities.ErrorType
import com.flickr.domain.usecases.GetRecentPhotosUseCase
import com.flickr.domain.usecases.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Error
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
    private val getSearchUseCase: GetSearchUseCase,
    private val networkManager: NetworkManager
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        fetchImages()
        observeNetworkEvents()
    }

    /**
     * Defaulting to using the search endpoint as recents safe-search does not actually work.
     */
    fun fetchImages() {
        viewModelScope.launch(Dispatchers.IO) {
            if (networkManager.hasConnection()) {
                setState {
                    copy(
                        loading = true,
                        error = null
                    )
                }

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
                    is RepositoryResult.Error -> {
                        setState {
                            copy(
                                error = response.error,
                                loading = false
                            )
                        }
                    }
                }
            } else {
                setState {
                    copy(
                        loading = false,
                        error = ErrorType.NetworkError
                    )
                }
            }
        }
    }

    private fun observeNetworkEvents() {
        viewModelScope.launch {
            networkManager.connectivityFlow.collectLatest {
                when (it) {
                    NetworkManager.NetworkState.Disconnected ->
                        setState {
                            copy(
                                loading = false,
                                error = ErrorType.NetworkError
                            )
                        }
                    else -> {
                        fetchImages()
                    }
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