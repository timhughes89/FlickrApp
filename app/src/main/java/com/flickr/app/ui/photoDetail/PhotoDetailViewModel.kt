package com.flickr.app.ui.photoDetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.flickr.app.common.BaseViewModel
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppImage
import com.flickr.domain.usecases.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class PhotoDetailViewState(
    val image: AppImage? = null,
    val loading: Boolean = true
)

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase
) : BaseViewModel<PhotoDetailViewState>(PhotoDetailViewState()) {

    fun fetchImage(photoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getImageUseCase.invoke(imageId = photoId)

            when (response) {
                is RepositoryResult.Success -> {
                    setState {
                        copy(
                            image = response.value,
                            loading = false
                        )
                    }
                }
                else -> {

                }
            }
        }
    }
}