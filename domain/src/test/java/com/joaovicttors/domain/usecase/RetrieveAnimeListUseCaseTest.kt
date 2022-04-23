package com.joaovicttors.domain.usecase

import com.joaovicttors.domain.aggregate.AnimeFormat
import com.joaovicttors.domain.aggregate.AnimeStatus
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

internal class RetrieveAnimeListUseCaseTest {

    private lateinit var animeRepository: AnimeRepository
    private lateinit var useCase: RetrieveAnimeListUseCase

    @Before
    fun setUp() {
        animeRepository = mock()
        useCase = RetrieveAnimeListUseCase(animeRepository)
    }

    @Test
    fun `retrieve anime list and the anime with id == 1 exist in database as favorite`() = runBlocking {
        val animeList = listOf(fakeAnime(1), fakeAnime(2))

        val expectedResponse = Response.Success(animeList)

        whenever(animeRepository.retrieveAnimeList()).thenReturn(expectedResponse)

        whenever(animeRepository.existAnimeById(any())).thenReturn(Response.Success(false))
        whenever(animeRepository.existAnimeById(animeList[0].id)).thenReturn(Response.Success(true))

        val response = useCase()

        verify(animeRepository).retrieveAnimeList()
        animeList.forEach { verify(animeRepository).existAnimeById(it.id) }

        assert(response == expectedResponse)
        assert((response as Response.Success).body == animeList)

        response.body.forEach { if (it.id == 1) assert(it.favorite) else assert(!it.favorite) }
    }

    @Test
    fun `retrieve anime list without anime as favorite`() = runBlocking {
        val animeList = listOf(fakeAnime(1), fakeAnime(2))

        val expectedResponse = Response.Success(animeList)

        whenever(animeRepository.retrieveAnimeList()).thenReturn(expectedResponse)

        whenever(animeRepository.existAnimeById(any())).thenReturn(Response.Success(false))

        val response = useCase()

        verify(animeRepository).retrieveAnimeList()
        animeList.forEach { verify(animeRepository).existAnimeById(it.id) }

        assert(response == expectedResponse)
        assert((response as Response.Success).body == animeList)

        response.body.forEach { assert(!it.favorite) }
    }

    @Test
    fun `retrieve anime list with all anime as favorite`() = runBlocking {
        val animeList = listOf(fakeAnime(1), fakeAnime(2))

        val expectedResponse = Response.Success(animeList)

        whenever(animeRepository.retrieveAnimeList()).thenReturn(expectedResponse)

        whenever(animeRepository.existAnimeById(any())).thenReturn(Response.Success(true))

        val response = useCase()

        verify(animeRepository).retrieveAnimeList()
        animeList.forEach { verify(animeRepository).existAnimeById(it.id) }

        assert(response == expectedResponse)
        assert((response as Response.Success).body == animeList)

        response.body.forEach { assert(it.favorite) }
    }

    @Test
    fun `retrieve anime list but exist anime by id method return error`() = runBlocking {
        val animeList = listOf(fakeAnime(1), fakeAnime(2))

        val expectedResponse = Response.Success(animeList)

        whenever(animeRepository.retrieveAnimeList()).thenReturn(expectedResponse)

        whenever(animeRepository.existAnimeById(any())).thenReturn(Response.Error(""))

        val response = useCase()

        verify(animeRepository).retrieveAnimeList()
        animeList.forEach { verify(animeRepository).existAnimeById(it.id) }

        assert(response == expectedResponse)
        assert((response as Response.Success).body == animeList)

        response.body.forEach { assert(!it.favorite) }
    }

    @Test
    fun `retrieve anime list but return error`() = runBlocking {
        val expectedResponse = Response.Error<List<Anime>>("")

        whenever(animeRepository.retrieveAnimeList()).thenReturn(expectedResponse)
        whenever(animeRepository.existAnimeById(any())).thenReturn(Response.Error(""))

        val response = useCase()

        verify(animeRepository).retrieveAnimeList()
        verify(animeRepository, never()).existAnimeById(any())

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