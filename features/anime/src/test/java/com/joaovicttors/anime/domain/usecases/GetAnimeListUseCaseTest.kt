package com.joaovicttors.anime.domain.usecases

import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.repositories.AnimeRepository
import com.joaovicttors.base.Response
import com.joaovicttors.base.usecase.BaseUseCase
import com.joaovicttors.base.usecase.utilities.NoParam
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetAnimeListUseCaseTest {

    private lateinit var animeRepository: AnimeRepository
    private lateinit var useCase: BaseUseCase<NoParam, Response<List<Anime>>>

    @Before
    fun before() {
        animeRepository = mockk()
        useCase = GetAnimeListUseCase(animeRepository)
    }

    @Test
    fun `Given local storage returns ERROR When use case called Then call remote storage`() =
        runBlocking { ->
            coEvery { animeRepository.getAnimeListFromLocalStorage() } returns Response.Error("")
            coEvery { animeRepository.getAnimeListFromRemoteStorage() } returns Response.Error("")

            useCase(NoParam())

            coVerify(exactly = 1) { animeRepository.getAnimeListFromRemoteStorage() }
        }

    @Test
    fun `Given local storage returns SUCCESS and data empty When use case called Then call remote storage`() =
        runBlocking { ->
            coEvery { animeRepository.getAnimeListFromLocalStorage() } returns Response.Success(emptyList())
            coEvery { animeRepository.getAnimeListFromRemoteStorage() } returns Response.Error("")

            useCase(NoParam())

            coVerify(exactly = 1) { animeRepository.getAnimeListFromRemoteStorage() }
        }

    @Test
    fun `Given local storage returns SUCCESS and data not empty When use case called Then return local data`() =
        runBlocking { ->
            val expectedAnimeList = listOf(mockk<Anime>())

            coEvery { animeRepository.getAnimeListFromLocalStorage() } returns Response.Success(expectedAnimeList)

            val actualResponse = useCase(NoParam())

            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedAnimeList, (actualResponse as Response.Success).data)
        }

    @Test
    fun `Given remote storage returns ERROR When use case called Then return this error`() =
        runBlocking { ->
            val expectedErrorMessage = "error"

            coEvery { animeRepository.getAnimeListFromLocalStorage() } returns Response.Error("")
            coEvery { animeRepository.getAnimeListFromRemoteStorage() } returns Response.Error(expectedErrorMessage)

            val actualResponse = useCase(NoParam())

            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedErrorMessage, (actualResponse as Response.Error).message)
        }

    @Test
    fun `Given remote storage returns SUCCESS When use case called Then insert data on local storage and returns`() =
        runBlocking { ->
            val expectedAnimeList = listOf(mockk<Anime>())

            coEvery { animeRepository.getAnimeListFromLocalStorage() } returns Response.Error("")
            coEvery { animeRepository.getAnimeListFromRemoteStorage() } returns Response.Success(expectedAnimeList)
            coEvery { animeRepository.insertAnimeListOnLocalStorage(expectedAnimeList) } returns Unit

            val actualResponse = useCase(NoParam())

            coVerify { animeRepository.insertAnimeListOnLocalStorage(expectedAnimeList) }

            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedAnimeList, (actualResponse as Response.Success).data)
        }
}