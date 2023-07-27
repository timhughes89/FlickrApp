package com.flickr.data.utils

import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.ErrorType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

object SafeCallHelper {

    suspend fun <T : Any> safeCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): RepositoryResult<T> {
        return withContext(dispatcher) {
            try {
                RepositoryResult.Success(apiCall.invoke())
            } catch (e: Exception) {
                RepositoryResult.Error(error = ErrorType.GenericError)
            }
        }
    }
}

