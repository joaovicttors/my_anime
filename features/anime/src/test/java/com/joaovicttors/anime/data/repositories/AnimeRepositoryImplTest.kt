package com.joaovicttors.anime.data.repositories

import com.joaovicttors.anime.data.datasources.local.AnimeLocalDataSource
import com.joaovicttors.anime.data.datasources.remote.AnimeRemoteDataSource
import com.joaovicttors.anime.domain.repositories.AnimeRepository
import io.mockk.mockk
import org.junit.Before

internal class AnimeRepositoryImplTest {

    private lateinit var localDataSource: AnimeLocalDataSource
    private lateinit var remoteDataSource: AnimeRemoteDataSource
    private lateinit var repository: AnimeRepository

    @Before
    fun before() {
        localDataSource = mockk()
        remoteDataSource = mockk()
        repository = AnimeRepositoryImpl(localDataSource, remoteDataSource)
    }
}