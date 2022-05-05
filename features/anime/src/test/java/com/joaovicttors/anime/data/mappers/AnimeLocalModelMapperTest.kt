package com.joaovicttors.anime.data.mappers

import com.joaovicttors.anime.data.models.AnimeLocalModel
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.enums.AnimeFormat
import com.joaovicttors.anime.domain.enums.AnimeStatus
import com.joaovicttors.base.BaseMapper
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

internal class AnimeLocalModelMapperTest {

    private lateinit var mapper: BaseMapper<AnimeLocalModel, Anime>

    @Before
    fun before() {
        mapper = AnimeLocalModelMapper()
    }

    @Test
    fun `when mapToDomainEntity should returns an anime domain entity`() {
        val expectedData = fakeAnime()
        val data = fakeAnimeLocalModel()

        val actualResponse = mapper.mapToDomainEntity(data)

        assertEquals(actualResponse, expectedData)
    }

    @Test
    fun `when mapFromDomainEntity returns an anime local model`() {
        val expectedData = fakeAnimeLocalModel()
        val data = fakeAnime()

        val actualResponse = mapper.mapFromDomainEntity(data)

        assertEquals(actualResponse, expectedData)
    }

    private fun fakeAnime(): Anime {
        return Anime(1, AnimeFormat.TV, AnimeStatus.FINISHED, mapOf(), mapOf(), "", 2.0F)
    }

    private fun fakeAnimeLocalModel(): AnimeLocalModel {
        return AnimeLocalModel(1, 0, 0, mapOf(), mapOf(), "", 2.0F)
    }
}