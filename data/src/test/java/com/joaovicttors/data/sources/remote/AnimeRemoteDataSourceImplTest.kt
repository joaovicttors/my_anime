package com.joaovicttors.data.sources.remote

import com.joaovicttors.data.mappers.AnimeResponseMapper
import com.joaovicttors.data.sources.remote.models.AnimeListResponse
import com.joaovicttors.data.sources.remote.models.AnimeResponse
import com.joaovicttors.data.sources.remote.models.SpecificAnimeResponse
import com.joaovicttors.data.sources.remote.service.AnimeRemoteService
import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

internal class AnimeRemoteDataSourceImplTest {

    private lateinit var remoteService: AnimeRemoteService
    private lateinit var responseMapper: AnimeResponseMapper

    private lateinit var dataSource: AnimeRemoteDataSource

    @Before
    fun setUp() {
        remoteService = mock()
        responseMapper = mock()

        dataSource = AnimeRemoteDataSourceImpl(remoteService, responseMapper)
    }

    @Test
    fun `retrieve anime list and return success response`() = runBlocking {
        val animeList = listOf(fakeAnimeDomainEntity(1), fakeAnimeDomainEntity(2))
        val animeResponseList = listOf(fakeAnimeResponse(1), fakeAnimeResponse(2))

        val expectedResponse = Response.Success(animeList)

        animeResponseList.forEachIndexed { index, anime ->
            whenever(responseMapper.mapToDomainEntity(anime)).thenReturn(animeList[index])
        }

        whenever(remoteService.retrieveAnimeListAsync(1)).thenReturn(fakeAnimeListResponse(animeResponseList))

        val response = dataSource.retrieveAnimeList()

        verify(remoteService).retrieveAnimeListAsync(1)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == animeList)
    }

    @Test
    fun `retrieve anime list and return error response`() = runBlocking {
        val errorMessage = ""

        val expectedResponse = Response.Error<List<Anime>>("")

        whenever(remoteService.retrieveAnimeListAsync(1)).thenThrow(RuntimeException(errorMessage))

        val response = dataSource.retrieveAnimeList()

        verify(remoteService).retrieveAnimeListAsync(1)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == errorMessage)
    }

    @Test
    fun `retrieve specific anime and return success response`() = runBlocking {
        val anime = fakeAnimeDomainEntity(1)
        val animeResponse = fakeAnimeResponse(1)

        val expectedResponse = Response.Success(anime)

        whenever(responseMapper.mapToDomainEntity(animeResponse)).thenReturn(anime)
        whenever(remoteService.retrieveSpecificAnimeAsync(anime.id)).thenReturn(fakeSpecificAnimeResponse(animeResponse))

        val response = dataSource.retrieveSpecificAnime(anime.id)

        verify(remoteService).retrieveSpecificAnimeAsync(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == anime)
    }

    @Test
    fun `retrieve specific anime and return error response`() = runBlocking {
        val anime = fakeAnimeResponse(1)

        val errorMessage = ""

        val expectedResponse = Response.Error<Anime>("")

        whenever(remoteService.retrieveSpecificAnimeAsync(anime.id)).thenThrow(RuntimeException(errorMessage))

        val response = dataSource.retrieveSpecificAnime(anime.id)

        verify(remoteService).retrieveSpecificAnimeAsync(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == errorMessage)
    }


    private fun fakeAnimeDomainEntity(id: Int) = Anime(
        id = id,
        format = AnimeFormat.TV,
        status = AnimeStatus.FINISHED,
        titles = mapOf(),
        descriptions = mapOf(),
        coverImage = "",
        score = 1.0F
    )

    private fun fakeAnimeResponse(id: Int) = AnimeResponse(
        id = id,
        format = AnimeFormat.TV.ordinal,
        status = AnimeStatus.FINISHED.ordinal,
        titles = mapOf(),
        descriptions = mapOf(),
        coverImage = "",
        score = 1.0F
    )

    private fun fakeAnimeListResponse(animeList: List<AnimeResponse>) = AnimeListResponse(
        statusCode = 200,
        message = "",
        data = AnimeListResponse.Data(
            currentPage = 1,
            count = animeList.size,
            documents = animeList,
            lastPage = 2
        )
    )

    private fun fakeSpecificAnimeResponse(anime: AnimeResponse) = SpecificAnimeResponse(
        statusCode = 200,
        message = "",
        data = anime
    )
}