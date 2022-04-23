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

internal class RetrieveSpecificAnimeUseCaseTest {

    private lateinit var animeRepository: AnimeRepository
    private lateinit var useCase: RetrieveSpecificAnimeUseCase

    @Before
    fun setUp() {
        animeRepository = mock()
        useCase = RetrieveSpecificAnimeUseCase(animeRepository)
    }

    @Test
    fun `retrieve specific anime by id and return success response`() = runBlocking {
        val animeId = 1
        val anime = fakeAnime(animeId)

        val expectedResponse = Response.Success(anime)

        whenever(animeRepository.retrieveSpecificAnime(animeId)).thenReturn(expectedResponse)

        val response = useCase(animeId)

        verify(animeRepository).retrieveSpecificAnime(animeId)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == anime)
    }

    @Test
    fun `retrieve specific anime by id and return error response`() = runBlocking {
        val expectedResponse = Response.Error<Anime>("")

        whenever(animeRepository.retrieveSpecificAnime(any())).thenReturn(expectedResponse)

        val response = useCase(any())

        verify(animeRepository).retrieveSpecificAnime(any())

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