package com.joaovicttors.domain.usecase

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository

class RetrieveAnimeListUseCase(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(): Response<List<Anime>> {
        return animeRepository.retrieveAnimeList().also { response ->
            if (response is Response.Success) {
                response.body.onEach { anime ->
                    animeRepository.existAnimeById(anime.id).also { exist ->
                        if (exist is Response.Success) anime.favorite = exist.body
                    }
                }
            }
        }
    }
}