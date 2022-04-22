package com.joaovicttors.domain.usecase

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.domain.entities.Response
import com.joaovicttors.domain.repositories.AnimeRepository

class RetrieveAnimeListUseCase(
    private val animeRepository: AnimeRepository
) {

    // TODO melhorar esse method
    suspend operator fun invoke(): Response<List<Anime>> {
        return animeRepository.retrieveAnimeList().let { response ->
            when (response) {
                is Response.Success -> response.apply {
                    this.body.forEach { anime ->
                        val test = animeRepository.existAnimeById(anime.id)
                        if (test is Response.Success) {
                            anime.favorite = test.body
                        }
                    }
                }
                is Response.Error -> response
            }
        }
    }
}