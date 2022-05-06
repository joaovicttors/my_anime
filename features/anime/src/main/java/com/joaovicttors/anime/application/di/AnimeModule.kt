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
import com.joaovicttors.anime.presentation.features.anime_list.view.AnimeListViewModel
import com.joaovicttors.base.BaseMapper
import com.joaovicttors.base.usecase.BaseUseCase
import com.joaovicttors.base.usecase.utilities.NoParam
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// TODO joao.santana
object AnimeModule {

    val dataLayer = module { ->

        single(named("test")) { AnimeLocalModelMapper() }
        single(named("test2")) { AnimeRemoteModelMapper() }

        single<AnimeLocalDataSource> { AnimeLocalDataSourceImpl(get(named("test")), get(), get()) }
        single<AnimeRemoteDataSource> { AnimeRemoteDataSourceImpl(get(named("test2")), get(), get()) }
    }

    val domainLayer = module { ->

        single<AnimeRepository> { AnimeRepositoryImpl(get(), get()) }

        single<BaseUseCase<NoParam, List<Anime>>> { GetAnimeListUseCase(get()) }
    }

    val presentationLayer = module { ->

        viewModel { AnimeListViewModel(get()) }
    }
}