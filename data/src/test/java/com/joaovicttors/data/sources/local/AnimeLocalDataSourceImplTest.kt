package com.joaovicttors.data.sources.local

import com.joaovicttors.data.mappers.AnimeEntityMapper
import com.joaovicttors.data.sources.local.models.AnimeEntity
import com.joaovicttors.data.sources.local.service.AnimeLocalService
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

internal class AnimeLocalDataSourceImplTest {

    private lateinit var localService: AnimeLocalService
    private lateinit var entityMapper: AnimeEntityMapper

    private lateinit var dataSource: AnimeLocalDataSource

    @Before
    fun setUp() {
        localService = mock()
        entityMapper = mock()

        dataSource = AnimeLocalDataSourceImpl(localService, entityMapper)
    }

    @Test
    fun `delete favorite anime and return success response`() = runBlocking {
        val animeDomainEntity = fakeAnimeDomainEntity()
        val animeEntity = fakeAnimeEntity()

        val expectedResponse = Response.Success(Unit)

        whenever(entityMapper.mapFromDomainEntity(animeDomainEntity)).thenReturn(animeEntity)
        whenever(localService.deleteFavoriteAnime(animeEntity)).thenReturn(Unit)

        val response = dataSource.deleteFavoriteAnime(animeDomainEntity)

        verify(entityMapper).mapFromDomainEntity(animeDomainEntity)
        verify(localService).deleteFavoriteAnime(animeEntity)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == Unit)
    }

    @Test
    fun `delete favorite anime and return error response`() = runBlocking {
        val animeDomainEntity = fakeAnimeDomainEntity()
        val animeEntity = fakeAnimeEntity()

        val errorMessage = "error"

        val expectedResponse = Response.Error<Unit>(errorMessage)

        whenever(entityMapper.mapFromDomainEntity(animeDomainEntity)).thenReturn(animeEntity)
        whenever(localService.deleteFavoriteAnime(animeEntity)).thenThrow(RuntimeException(errorMessage))

        val response = dataSource.deleteFavoriteAnime(animeDomainEntity)

        verify(entityMapper).mapFromDomainEntity(animeDomainEntity)
        verify(localService).deleteFavoriteAnime(animeEntity)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == errorMessage)
    }

    @Test
    fun `insert favorite anime and return success response`() = runBlocking {
        val animeDomainEntity = fakeAnimeDomainEntity()
        val animeEntity = fakeAnimeEntity()

        val expectedResponse = Response.Success(Unit)

        whenever(entityMapper.mapFromDomainEntity(animeDomainEntity)).thenReturn(animeEntity)
        whenever(localService.insertFavoriteAnime(animeEntity)).thenReturn(Unit)

        val response = dataSource.insertFavoriteAnime(animeDomainEntity)

        verify(entityMapper).mapFromDomainEntity(animeDomainEntity)
        verify(localService).insertFavoriteAnime(animeEntity)

        assert(response == expectedResponse)
        assert((response as Response.Success).body == Unit)
    }

    @Test
    fun `insert favorite anime and return error response`() = runBlocking {
        val animeDomainEntity = fakeAnimeDomainEntity()
        val animeEntity = fakeAnimeEntity()

        val errorMessage = "error"

        val expectedResponse = Response.Error<Unit>(errorMessage)

        whenever(entityMapper.mapFromDomainEntity(animeDomainEntity)).thenReturn(animeEntity)
        whenever(localService.insertFavoriteAnime(animeEntity)).thenThrow(RuntimeException(errorMessage))

        val response = dataSource.insertFavoriteAnime(animeDomainEntity)

        verify(entityMapper).mapFromDomainEntity(animeDomainEntity)
        verify(localService).insertFavoriteAnime(animeEntity)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == errorMessage)
    }

    @Test
    fun `exist anime by id and the anime exist in database`() = runBlocking {
        val anime = fakeAnimeDomainEntity()

        val expectedResponse = Response.Success(true)

        whenever(localService.existAnimeById(anime.id)).thenReturn(true)

        val response = dataSource.existAnimeById(anime.id)

        verify(localService).existAnimeById(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Success).body)
    }

    @Test
    fun `exist anime by id and the anime not exist in database`() = runBlocking {
        val anime = fakeAnimeDomainEntity()

        val expectedResponse = Response.Success(false)

        whenever(localService.existAnimeById(anime.id)).thenReturn(false)

        val response = dataSource.existAnimeById(anime.id)

        verify(localService).existAnimeById(anime.id)

        assert(response == expectedResponse)
        assert(!(response as Response.Success).body)
    }

    @Test
    fun `exist anime by id and return error response`() = runBlocking {
        val anime = fakeAnimeDomainEntity()

        val errorMessage = "error"

        val expectedResponse = Response.Error<Boolean>(errorMessage)

        whenever(localService.existAnimeById(anime.id)).thenThrow(RuntimeException(errorMessage))

        val response = dataSource.existAnimeById(anime.id)

        verify(localService).existAnimeById(anime.id)

        assert(response == expectedResponse)
        assert((response as Response.Error).message == errorMessage)
    }

    private fun fakeAnimeDomainEntity() = Anime(
        id = 1,
        format = AnimeFormat.TV,
        status = AnimeStatus.FINISHED,
        titles = mapOf(),
        descriptions = mapOf(),
        coverImage = "",
        score = 1.0F
    )

    private fun fakeAnimeEntity() = AnimeEntity(
        id = 1,
        format = AnimeFormat.TV.ordinal,
        status = AnimeStatus.FINISHED.ordinal,
        titles = mapOf(),
        descriptions = mapOf(),
        coverImage = "",
        score = 1.0F
    )
}