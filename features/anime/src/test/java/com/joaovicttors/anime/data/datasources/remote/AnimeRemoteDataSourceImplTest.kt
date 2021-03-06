package com.joaovicttors.anime.data.datasources.remote

import com.joaovicttors.anime.data.datasources.remote.service.RetrofitAnimeService
import com.joaovicttors.anime.data.mappers.AnimeRemoteModelMapper
import com.joaovicttors.anime.data.models.AnimeRemoteModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.base.Response
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class AnimeRemoteDataSourceImplTest {

    private lateinit var mapper: AnimeRemoteModelMapper
    private lateinit var service: RetrofitAnimeService
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var dataSource: AnimeRemoteDataSource

    @Before
    fun before() {
        mapper = mockk(relaxed = true)
        service = mockk(relaxed = true)
        dispatcher = UnconfinedTestDispatcher()
        dataSource = AnimeRemoteDataSourceImpl(mapper, service, dispatcher)
    }

    @Test
    fun `when getAnimeList called it should getAll from service`() =
        runBlocking { ->
            dataSource.getAnimeList()

            coVerify(exactly = 1) { service.getAnimeList() }
        }

    @Test
    fun `when getAll from service throws an error should getAnimeList returns error response`() =
        runBlocking { ->
            val expectedErrorMessage = "error"

            coEvery { service.getAnimeList() } throws RuntimeException(expectedErrorMessage)

            val actualResponse = dataSource.getAnimeList()

            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedErrorMessage, (actualResponse as Response.Error).message)
        }

    @Test
    fun `when getAll from service returns success response should getAnimeList returns success response`() =
        runBlocking { ->
            val expectedAnimeList = listOf<Anime>(mockk())
            val animeListRemoteModel = mockk<AnimeRemoteModel>()
            val animeRemoteModelList = listOf<AnimeRemoteModel.Documents>(mockk())

            coEvery { animeListRemoteModel.data } returns mockk()
            coEvery { animeListRemoteModel.data.documents } returns animeRemoteModelList

            coEvery { service.getAnimeList() } returns animeListRemoteModel
            coEvery { mapper.mapToDomainEntity(animeRemoteModelList.first()) } returns expectedAnimeList.first()

            val actualResponse = dataSource.getAnimeList()

            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedAnimeList, (actualResponse as Response.Success).data)
        }
}