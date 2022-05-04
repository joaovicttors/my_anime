package com.joaovicttors.anime.data.datasources.local

import com.joaovicttors.anime.data.datasources.local.service.RoomAnimeService
import com.joaovicttors.anime.data.mappers.AnimeLocalModelMapper
import com.joaovicttors.anime.data.models.AnimeLocalModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.Response
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class AnimeLocalDataSourceImplTest {

    private lateinit var mapper: AnimeLocalModelMapper
    private lateinit var service: RoomAnimeService
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var dataSource: AnimeLocalDataSource

    @Before
    fun before() {
        mapper = mockk(relaxed = true)
        service = mockk(relaxed = true)
        dispatcher = UnconfinedTestDispatcher()
        dataSource = AnimeLocalDataSourceImpl(mapper, service, dispatcher)
    }

    @Test
    fun `when getAnimeList called it should getAll from service`() =
        runBlocking { ->
            dataSource.getAnimeList()

            coVerify(exactly = 1) { service.getAll() }
        }

    @Test
    fun `when getAll from service throws an error should getAnimeList returns error response`() =
        runBlocking { ->
            val expectedErrorMessage = "error"

            coEvery { service.getAll() } throws RuntimeException(expectedErrorMessage)

            val actualResponse = dataSource.getAnimeList()

            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedErrorMessage, (actualResponse as Response.Error).message)
        }

    @Test
    fun `when getAll from service returns success response should getAnimeList returns success response`() =
        runBlocking { ->
            val expectedAnimeList = listOf<Anime>(mockk())
            val animeLocalModelList = listOf<AnimeLocalModel>(mockk())

            coEvery { service.getAll() } returns animeLocalModelList
            coEvery { mapper.mapToDomainEntity(animeLocalModelList.first()) } returns expectedAnimeList.first()

            val actualResponse = dataSource.getAnimeList()

            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedAnimeList, (actualResponse as Response.Success).data)
        }

    @Test
    fun `when insertAnimeList called it should insertAll from service`() =
        runBlocking { ->
            dataSource.insertAnimeList(emptyList())

            coVerify(exactly = 1) { service.insertAll(emptyList()) }
        }

    @Test
    fun `when insertAll from service throws an error should insertAnimeList returns unit`() =
        runBlocking { ->
            val expectedErrorMessage = "error"

            coEvery { service.insertAll(any()) } throws RuntimeException(expectedErrorMessage)

            val actualResponse = dataSource.insertAnimeList(emptyList())

            assertEquals(Unit, actualResponse)
        }

    @Test
    fun `when insertAll from service returns success response should insertAnimeList returns unit`() =
        runBlocking { ->
            val animeList = listOf<Anime>(mockk())
            val animeLocalModelList = listOf<AnimeLocalModel>(mockk())

            coEvery { service.insertAll(animeLocalModelList) } returns Unit
            coEvery { mapper.mapFromDomainEntity(animeList.first()) } returns animeLocalModelList.first()

            val actualResponse = dataSource.insertAnimeList(animeList)

            assertEquals(Unit, actualResponse)
        }
}