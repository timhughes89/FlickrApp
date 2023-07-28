package com.flickr.app.ui.photoDetail

import app.cash.turbine.test
import com.flickr.app.common.CoroutineTestExtension
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppImage
import com.flickr.domain.entities.AppPhotoDates
import com.flickr.domain.entities.ErrorType
import com.flickr.domain.usecases.GetImageUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineTestExtension::class)
internal class PhotoDetailViewModelTest {

    private val getImageUseCase: GetImageUseCase = mockk()
    private val mockImage = AppImage(id = "", "", "", "", "", AppPhotoDates(posted = "", taken = ""), views = "")

    private lateinit var sut: PhotoDetailViewModel

    @BeforeEach
    fun setupEach() {
        sut = PhotoDetailViewModel(getImageUseCase)
    }

    @Test
    fun testViewModel_initialState() = TestScope().runTest {
        sut.state.test {
            assertEquals(PhotoDetailViewState(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun testViewModel_callReturnsSuccess_stateUpdated_photosNotEmptyOrNull() = TestScope().runTest {
        sut.state.test {
            var state = awaitItem()
            assertTrue(state.image == null)

            coEvery { getImageUseCase.invoke(any()) } returns RepositoryResult.Success(mockImage)
            coVerify(exactly = 1) { getImageUseCase.invoke(any()) }

            state = awaitItem()
            assertTrue(state.image != null)
        }
    }

    @Test
    fun testViewModel_callReturnsFailure_stateUpdated() = TestScope().runTest {
        sut.state.test {
            var state = awaitItem()
            assertTrue(state.image == null)

            coEvery { getImageUseCase.invoke(any()) } returns RepositoryResult.Error(ErrorType.GenericError)
            coVerify(exactly = 1) { getImageUseCase.invoke(any()) }

            state = awaitItem()
            assertTrue(state.image == null)
        }
    }
}