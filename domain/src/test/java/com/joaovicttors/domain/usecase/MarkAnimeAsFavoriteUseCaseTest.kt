package com.joaovicttors.domain.usecase

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

internal class MarkAnimeAsFavoriteUseCaseTest {

    private lateinit var animeRepository: AnimeRepository
    private lateinit var useCase: MarkAnimeAsFavoriteUseCase

    @Before
    fun setUp() {
        animeRepository = mock()
        useCase = MarkAnimeAsFavoriteUseCase(animeRepository)
    }

    @Test
    fun `delete anime from database when favorite button is clicked and anime is already in database as favorite `() =
        runBlocking { ->
            val anime = fakeAnime().apply { favorite = true }
            val expectedResponse = Response.Success(Unit)

            whenever(animeRepository.deleteAnime(anime)).thenReturn(expectedResponse)

            val response = useCase(anime)

            verify(animeRepository).deleteAnime(anime)

            assert(response == expectedResponse)
            assert(!anime.favorite)

        }

    @Test
    fun `don't swap favorite variable when try delete anime but this function return error `() =
        runBlocking { ->
            val anime = fakeAnime().apply { favorite = true }
            val expectedResponse = Response.Error<Unit>("error")

            whenever(animeRepository.deleteAnime(anime)).thenReturn(expectedResponse)

            val response = useCase(anime)

            verify(animeRepository).deleteAnime(anime)

            assert(response == expectedResponse)
            assert(anime.favorite)
        }

    @Test
    fun `insert anime into the database when the favorite button is clicked and the anime is not yet in the database as a favorite`() =
        runBlocking { ->
            val anime = fakeAnime().apply { favorite = false }
            val expectedResponse = Response.Success(Unit)

            whenever(animeRepository.insertAnime(anime)).thenReturn(expectedResponse)

            val response = useCase(anime)

            verify(animeRepository).insertAnime(anime)

            assert(response == expectedResponse)
            assert(anime.favorite)
        }

    @Test
    fun `don't swap favorite variable when try insert anime but this function return error `() =
        runBlocking { ->
            val anime = fakeAnime().apply { favorite = false }
            val expectedResponse = Response.Error<Unit>("error")

            whenever(animeRepository.insertAnime(anime)).thenReturn(expectedResponse)

            val response = useCase(anime)

            verify(animeRepository).insertAnime(anime)

            assert(response == expectedResponse)
            assert(!anime.favorite)
        }

    private fun fakeAnime() = Anime(
        id = 1,
        format = AnimeFormat.TV,
        status = AnimeStatus.FINISHED,
        titles = mapOf(),
        descriptions = mapOf(),
        coverImage = "",
        score = 1.0F
    )
}