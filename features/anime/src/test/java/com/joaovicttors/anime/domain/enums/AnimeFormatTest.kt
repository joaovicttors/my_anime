package com.joaovicttors.anime.domain.enums

import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class AnimeFormatTest {

    @Test
    fun `when value is 0 should return a TV format`() {
        assertEquals(AnimeFormat.TV, AnimeFormat.value(index = 0))
    }

    @Test
    fun `when value is 1 should return a TV_SHORT format`() {
        assertEquals(AnimeFormat.TV_SHORT, AnimeFormat.value(index = 1))
    }

    @Test
    fun `when value is 2 should return a MOVIE format`() {
        assertEquals(AnimeFormat.MOVIE, AnimeFormat.value(index = 2))
    }

    @Test
    fun `when value is 3 should return a SPECIAL format`() {
        assertEquals(AnimeFormat.SPECIAL, AnimeFormat.value(index = 3))
    }

    @Test
    fun `when value is 4 should return a OVA format`() {
        assertEquals(AnimeFormat.OVA, AnimeFormat.value(index = 4))
    }

    @Test
    fun `when value is 5 should return a ONA format`() {
        assertEquals(AnimeFormat.ONA, AnimeFormat.value(index = 5))
    }

    @Test
    fun `when value is 6 should return a MUSIC format`() {
        assertEquals(AnimeFormat.MUSIC, AnimeFormat.value(index = 6))
    }

    @Test
    fun `when value is out of range should return TV as default format`() {
        assertEquals(AnimeFormat.TV, AnimeFormat.value(index = 7))
    }
}