package com.flickr.app

import com.flickr.app.common.CoroutineTestExtension
import com.flickr.domain.RepositoryResult
import com.flickr.domain.entities.AppImage
import com.flickr.domain.entities.AppPhotoDates
import com.flickr.domain.entities.ErrorType
import com.flickr.domain.gateways.ImagesGateway
import com.flickr.domain.usecases.GetImageUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineTestExtension::class)
internal class GetImageUseCaseTest {

    private val imagesGateway: ImagesGateway = mockk()
    private lateinit var sut: GetImageUseCase

    private val mockImage = AppImage(id = "", "", "", "", "", AppPhotoDates(posted = "", taken = ""), views = "")

    @BeforeEach
    fun setup() {
        sut = GetImageUseCase(imagesGateway)
    }

    @Test
    fun testUseCase_returnsRepositoryResult_success() = runTest {
        val expected = RepositoryResult.Success(mockImage)

        coEvery { sut.invoke(any())} returns expected

        val result = sut.invoke("0")
        coVerify(exactly = 1) { sut.invoke(any()) }

        assertEquals(expected, result)
    }

    @Test
    fun testUseCase_returnsRepositoryResult_genericError() = runBlocking {
        val expected = RepositoryResult.Error(ErrorType.GenericError)

        coEvery { sut.invoke(any()) } returns expected

        val result = sut.invoke("1")
        coVerify(exactly = 1) { sut.invoke(any()) }

        assertEquals(expected, result)
    }
}