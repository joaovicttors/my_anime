package com.joaovicttors.data.repositories

import com.joaovicttors.data.sources.local.AnimeLocalDataSource
import com.joaovicttors.data.sources.remote.AnimeRemoteDataSource
import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class AnimeRepositoryImplTest {

    private lateinit var localDataSource: AnimeLocalDataSource
    private lateinit var remoteDataSource: AnimeRemoteDataSource

    private lateinit var animeRepository: AnimeRepository

    @Before
    fun setUp() {
        localDataSource = mock()
        remoteDataSource = mock()

        animeRepository = AnimeRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `delete anime and return success response`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Success(Unit)

        whenever(localDataSource.deleteFavoriteAnime(anime)).thenReturn(expectedResponse)

        val response = animeRepository.deleteAnime(anime)

        verify(localDataSource).deleteFavoriteAnime(anime)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == Unit)
    }

    @Test
    fun `delete anime and return error response`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Error<Unit>("")

        whenever(localDataSource.deleteFavoriteAnime(anime)).thenReturn(expectedResponse)

        val response = animeRepository.deleteAnime(anime)

        verify(localDataSource).deleteFavoriteAnime(anime)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == "")
    }

    @Test
    fun `insert anime and return success response`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Success(Unit)

        whenever(localDataSource.insertFavoriteAnime(anime)).thenReturn(expectedResponse)

        val response = animeRepository.insertAnime(anime)

        verify(localDataSource).insertFavoriteAnime(anime)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == Unit)
    }

    @Test
    fun `insert anime and return error response`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Error<Unit>("")

        whenever(localDataSource.insertFavoriteAnime(anime)).thenReturn(expectedResponse)

        val response = animeRepository.insertAnime(anime)

        verify(localDataSource).insertFavoriteAnime(anime)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == "")
    }

    @Test
    fun `exist anime by id and the anime exist in database`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Success(true)

        whenever(localDataSource.existAnimeById(anime.id)).thenReturn(expectedResponse)

        val response = animeRepository.existAnimeById(anime.id)

        verify(localDataSource).existAnimeById(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Success).body)
    }

    @Test
    fun `exist anime by id and the anime not exist in database`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Success(false)

        whenever(localDataSource.existAnimeById(anime.id)).thenReturn(expectedResponse)

        val response = animeRepository.existAnimeById(anime.id)

        verify(localDataSource).existAnimeById(anime.id)

        assert(response == expectedResponse)
        assert(!(response as Response.Success).body)
    }

    @Test
    fun `exist anime by id and return error response`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Error<Boolean>("")

        whenever(localDataSource.existAnimeById(anime.id)).thenReturn(expectedResponse)

        val response = animeRepository.existAnimeById(anime.id)

        verify(localDataSource).existAnimeById(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == "")
    }

    @Test
    fun `retrieve anime list and return success response`() = runBlocking {
        val animeList = listOf(fakeAnime(1), fakeAnime(2))

        val expectedResponse = Response.Success(animeList)

        whenever(remoteDataSource.retrieveAnimeList()).thenReturn(expectedResponse)

        val response = animeRepository.retrieveAnimeList()

        verify(remoteDataSource).retrieveAnimeList()

        assert(response == expectedResponse)
        assert((response as Response.Success).body == animeList)
    }

    @Test
    fun `retrieve anime list and return error response`() = runBlocking {
        val expectedResponse = Response.Error<List<Anime>>("")

        whenever(remoteDataSource.retrieveAnimeList()).thenReturn(expectedResponse)

        val response = animeRepository.retrieveAnimeList()

        verify(remoteDataSource).retrieveAnimeList()

        assert(response == expectedResponse)
        assert((response as Response.Error).message == "")
    }

    @Test
    fun `retrieve specific anime and return success response`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Success(anime)

        whenever(remoteDataSource.retrieveSpecificAnime(anime.id)).thenReturn(expectedResponse)

        val response = animeRepository.retrieveSpecificAnime(anime.id)

        verify(remoteDataSource).retrieveSpecificAnime(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == anime)
    }

    @Test
    fun `retrieve specific anime and return error response`() = runBlocking {
        val anime = fakeAnime(1)

        val expectedResponse = Response.Error<Anime>("")

        whenever(remoteDataSource.retrieveSpecificAnime(anime.id)).thenReturn(expectedResponse)

        val response = animeRepository.retrieveSpecificAnime(anime.id)

        verify(remoteDataSource).retrieveSpecificAnime(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == "")
    }


    private fun fakeAnime(id: Int) = Anime(
        id = id,
        format = AnimeFormat.TV,
        status = AnimeStatus.FINISHED,
        titles = mapOf(),
        descriptions = mapOf(),
        coverImage = "",
        score = 1.0F
    )
}