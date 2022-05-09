package com.joaovicttors.anime.presentation.features.anime_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.joaovicttors.anime.R
import com.joaovicttors.anime.databinding.FragmentAnimeDetailBinding
import com.joaovicttors.base.BaseFragment
import com.squareup.picasso.Picasso
import org.koin.core.component.inject

class AnimeDetailView : BaseFragment() {

    override val viewModel: AnimeDetailViewModel by inject()

    private lateinit var binding: FragmentAnimeDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnimeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = ""

        binding.toolbar.setNavigationIcon(R.drawable.ic_close_48px)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        arguments?.also { binding.anime = AnimeDetailViewArgs.fromBundle(it).anime }

        Picasso.get()
            .load(binding.anime!!.coverImage)
            .into(binding.animeImage)
    }
}