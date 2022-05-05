package com.joaovicttors.anime.domain.enums

import junit.framework.TestCase
import org.junit.Test

internal class AnimeStatusTest {

    @Test
    fun `when value is 0 should return a FINISHED status`() {
        TestCase.assertEquals(AnimeStatus.FINISHED, AnimeStatus.value(index = 0))
    }

    @Test
    fun `when value is 1 should return a RELEASING status`() {
        TestCase.assertEquals(AnimeStatus.RELEASING, AnimeStatus.value(index = 1))
    }

    @Test
    fun `when value is 2 should return a NOT_YET_RELEASE status`() {
        TestCase.assertEquals(AnimeStatus.NOT_YET_RELEASE, AnimeStatus.value(index = 2))
    }

    @Test
    fun `when value is 3 should return a CANCELLED status`() {
        TestCase.assertEquals(AnimeStatus.CANCELLED, AnimeStatus.value(index = 3))
    }

    @Test
    fun `when value is out of range should return UNKNOWN as default status`() {
        TestCase.assertEquals(AnimeStatus.UNKNOWN, AnimeStatus.value(index = 7))
    }
}