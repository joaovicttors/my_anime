package com.joaovicttors.anime.data.mappers

import com.joaovicttors.anime.data.models.AnimeRemoteModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.enums.AnimeFormat
import com.joaovicttors.anime.domain.enums.AnimeStatus
import com.joaovicttors.base.BaseMapper
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

internal class AnimeRemoteModelMapperTest {

    private lateinit var mapper: BaseMapper<AnimeRemoteModel.Documents, Anime>

    @Before
    fun before() {
        mapper = AnimeRemoteModelMapper()
    }

    @Test
    fun `when mapToDomainEntity should return an anime domain entity`() {
        val expectedData = fakeAnime()
        val data = fakeAnimeRemoteModelDocuments()

        val actualResponse = mapper.mapToDomainEntity(data)

        assertEquals(actualResponse, expectedData)
    }

    @Test(expected = NotImplementedError::class)
    fun `when mapFromDomainEntity should throws NotImplementedError`() {
        mapper.mapFromDomainEntity(mockk())
    }

    private fun fakeAnime(): Anime {
        return Anime(1, AnimeFormat.TV, AnimeStatus.FINISHED, mapOf(), mapOf(), "", 2.0F)
    }

    private fun fakeAnimeRemoteModelDocuments(): AnimeRemoteModel.Documents {
        return AnimeRemoteModel.Documents(1, 0, 0, mapOf(), mapOf(), "", 2.0F)
    }


}