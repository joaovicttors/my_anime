package com.joaovicttors.domain.usecase

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository

class RetrieveSpecificAnimeUseCase(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(id: Int): Response<Anime> {
        return animeRepository.retrieveSpecificAnime(id)
    }
}