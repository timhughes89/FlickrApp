package com.flickr.app.ui.userProfile

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.flickr.app.common.BaseViewModel
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppPhoto
import com.flickr.domain.entities.AppUser
import com.flickr.domain.usecases.GetUserProfilePhotosUseCase
import com.flickr.domain.usecases.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class UserProfileViewState(
    val user: AppUser? = null,
    val userPhotos: List<AppPhoto>? = null,
    val loading: Boolean = true,
)

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserProfilePhotosUseCase: GetUserProfilePhotosUseCase
) : BaseViewModel<UserProfileViewState>(UserProfileViewState()) {

    fun fetchUserInfo(userId: String) {
        fetchUser(userId)
        fetchUserPhotos(userId)
    }

    private fun fetchUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUserProfileUseCase.invoke(userId)

            when (response) {
                is RepositoryResult.Success -> {
                    setState {
                        copy(
                            user = response.value,
                            loading = false
                        )
                    }
                }
                else -> {
                    // Present some feeback to the user, possibly a snackbar.
                }
            }
        }
    }

    private fun fetchUserPhotos(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUserProfilePhotosUseCase.invoke(userId)

            when (response) {
                is RepositoryResult.Success -> {
                    setState {
                        copy(
                            userPhotos = response.value
                        )
                    }
                }
                else -> {
                    // Present some feeback to the user, possibly a snackbar.
                }
            }
        }
    }
}