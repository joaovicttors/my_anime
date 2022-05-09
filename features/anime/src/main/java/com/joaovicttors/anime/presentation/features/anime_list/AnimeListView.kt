package com.joaovicttors.anime.presentation.features.anime_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaovicttors.anime.R
import com.joaovicttors.anime.databinding.FragmentAnimeListBinding
import com.joaovicttors.anime.domain.entities.Anime
import com.joaovicttors.anime.presentation.features.anime_list.adapter.AnimeListAdapter
import com.joaovicttors.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class AnimeListView : BaseFragment() {

    override val viewModel: AnimeListViewModel by inject()

    private lateinit var adapter: AnimeListAdapter
    private lateinit var binding: FragmentAnimeListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        adapter = AnimeListAdapter(::onSpecificAnimeClicked)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        recyclerViewFactory()
        viewModel.getAnimeList()
        viewStateObserver()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = ""
    }

    private fun recyclerViewFactory() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun viewStateObserver() {
        lifecycleScope.launch { ->
            repeatOnLifecycle(Lifecycle.State.STARTED) { ->
                viewModel.viewState.collect { viewState ->
                    adapter.animeList = viewState.data
                    loadingStateBehavior(viewState.isLoading)
                    errorStateBehavior(viewState.errorMessage)
                }
            }
        }
    }

    private fun loadingStateBehavior(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmer.startShimmer()
            binding.shimmer.visibility = View.VISIBLE
        } else {
            binding.shimmer.stopShimmer()
            binding.shimmer.visibility = View.GONE
        }
    }

    private fun errorStateBehavior(errorMessage: String?) {
        if (errorMessage != null) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun onSpecificAnimeClicked(anime: Anime) {
        findNavController().navigate(AnimeListViewDirections.onSpecificAnimeClicked(anime))
    }
}