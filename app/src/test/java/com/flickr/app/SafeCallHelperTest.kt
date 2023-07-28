package com.flickr.app

import com.flickr.data.utils.SafeCallHelper
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.ErrorType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class SafeCallHelperTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun returnsSuccesfully_emitsResultSuccess() = runTest {
        val result = true
        val callResult = SafeCallHelper.safeCall(dispatcher) { result }
        assertEquals(RepositoryResult.Success(result), callResult)
    }

    @Test
    fun throwUnknownException_emitsGenericError() = runTest {
        val result = SafeCallHelper.safeCall(dispatcher) {
            throw IllegalStateException()
        }
        assertEquals(RepositoryResult.Error(ErrorType.GenericError), result)
    }
}