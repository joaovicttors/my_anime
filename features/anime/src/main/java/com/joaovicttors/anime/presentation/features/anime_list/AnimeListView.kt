package com.joaovicttors.anime.presentation.features.anime_list

import com.joaovicttors.base.BaseFragment
import com.joaovicttors.base.BaseViewModel
import org.koin.core.component.inject

class AnimeListView : BaseFragment() {

    override val viewModel: BaseViewModel by inject()

    private lateinit var binding: String

}