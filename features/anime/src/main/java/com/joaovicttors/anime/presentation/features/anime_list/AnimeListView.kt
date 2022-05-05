package com.joaovicttors.anime.presentation.features.anime_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joaovicttors.anime.databinding.FragmentAnimeListBinding
import com.joaovicttors.base.BaseFragment
import com.joaovicttors.base.BaseViewModel
import org.koin.core.component.inject

class AnimeListView : BaseFragment() {

    override val viewModel: BaseViewModel by inject()

    private lateinit var binding: FragmentAnimeListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnimeListBinding.inflate(inflater, container, true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TEST", "TEST")
    }
}