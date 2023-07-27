package com.flickr.domain

import com.flickr.domain.entities.ErrorType

sealed class RepositoryResult<out T : Any> {
    data class Success<out T : Any>(val value: T) : RepositoryResult<T>()
    data class Error(val error: ErrorType) : RepositoryResult<Nothing>()
}