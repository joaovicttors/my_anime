package com.joaovicttors.anime.data.repositories

import com.joaovicttors.anime.data.datasources.local.AnimeLocalDataSource
import com.joaovicttors.anime.data.datasources.remote.AnimeRemoteDataSource
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.repositories.AnimeRepository
import com.joaovicttors.base.Response
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class AnimeRepositoryImplTest {

    private lateinit var localDataSource: AnimeLocalDataSource
    private lateinit var remoteDataSource: AnimeRemoteDataSource
    private lateinit var repository: AnimeRepository

    @Before
    fun before() {
        localDataSource = mockk(relaxed = true)
        remoteDataSource = mockk(relaxed = true)
        repository = AnimeRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `when getAnimeListFromLocalStorage called it should getAnimeList from local data source`() =
        runBlocking { ->
            repository.getAnimeListFromLocalStorage()

            coVerify { localDataSource.getAnimeList() }
        }

    @Test
    fun `when localDataSource returns error response should getAnimeListFromLocalStorage returns error response`() =
        runBlocking { ->
            val expectedErrorMessage = "error"

            coEvery { localDataSource.getAnimeList() } returns Response.Error(expectedErrorMessage)

            val actualResponse = repository.getAnimeListFromLocalStorage()

            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedErrorMessage, (actualResponse as Response.Error).message)
        }

    @Test
    fun `when localDataSource returns success response should getAnimeListFromLocalStorage returns success response`() =
        runBlocking { ->
            val expectedAnimeList = listOf(mockk<Anime>())

            coEvery { localDataSource.getAnimeList() } returns Response.Success(expectedAnimeList)

            val actualResponse = repository.getAnimeListFromLocalStorage()

            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedAnimeList, (actualResponse as Response.Success).data)
        }

    @Test
    fun `when getAnimeListFromRemoteStorage called it should getAnimeList from remote data source`() =
        runBlocking { ->
            repository.getAnimeListFromRemoteStorage()

            coVerify { remoteDataSource.getAnimeList() }
        }

    @Test
    fun `when localDataSource returns error response should getAnimeListFromRemoteStorage returns error response`() =
        runBlocking { ->
            val expectedErrorMessage = "error"

            coEvery { remoteDataSource.getAnimeList() } returns Response.Error(expectedErrorMessage)

            val actualResponse = repository.getAnimeListFromRemoteStorage()

            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedErrorMessage, (actualResponse as Response.Error).message)
        }

    @Test
    fun `when localDataSource returns success response should getAnimeListFromRemoteStorage returns success response`() =
        runBlocking { ->
            val expectedAnimeList = listOf(mockk<Anime>())

            coEvery { remoteDataSource.getAnimeList() } returns Response.Success(expectedAnimeList)

            val actualResponse = repository.getAnimeListFromRemoteStorage()

            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedAnimeList, (actualResponse as Response.Success).data)
        }

    @Test
    fun `when insertAnimeListOnLocalStorage called it should insertAnimeList on local data source`() =
        runBlocking { ->
            repository.insertAnimeListOnLocalStorage(emptyList())

            coVerify { localDataSource.insertAnimeList(any()) }
        }
}