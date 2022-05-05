package com.joaovicttors.anime.application.di

import com.joaovicttors.anime.data.datasources.local.AnimeLocalDataSource
import com.joaovicttors.anime.data.datasources.local.AnimeLocalDataSourceImpl
import com.joaovicttors.anime.data.datasources.remote.AnimeRemoteDataSource
import com.joaovicttors.anime.data.datasources.remote.AnimeRemoteDataSourceImpl
import com.joaovicttors.anime.data.mappers.AnimeLocalModelMapper
import com.joaovicttors.anime.data.mappers.AnimeRemoteModelMapper
import com.joaovicttors.anime.data.models.AnimeLocalModel
import com.joaovicttors.anime.data.models.AnimeRemoteModel
import com.joaovicttors.anime.data.repositories.AnimeRepositoryImpl
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.domain.repositories.AnimeRepository
import com.joaovicttors.anime.domain.usecases.GetAnimeListUseCase
import com.joaovicttors.anime.presentation.features.anime_list.AnimeListViewModel
import com.joaovicttors.base.BaseMapper
import com.joaovicttors.base.usecase.BaseUseCase
import com.joaovicttors.base.usecase.utilities.NoParam
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AnimeModule {

    val dataLayer = module { ->

        single<BaseMapper<AnimeLocalModel, Anime>> { AnimeLocalModelMapper() }
        single<BaseMapper<AnimeRemoteModel.Documents, Anime>> { AnimeRemoteModelMapper() }

        single<AnimeLocalDataSource> { AnimeLocalDataSourceImpl(get(), get(), get()) }
        single<AnimeRemoteDataSource> { AnimeRemoteDataSourceImpl(get(), get(), get()) }
    }

    val domainLayer = module { ->

        single<AnimeRepository> { AnimeRepositoryImpl(get(), get()) }

        single<BaseUseCase<NoParam, List<Anime>>> { GetAnimeListUseCase(get()) }
    }

    val presentationLayer = module { ->

        viewModel { AnimeListViewModel(get()) }
    }
}