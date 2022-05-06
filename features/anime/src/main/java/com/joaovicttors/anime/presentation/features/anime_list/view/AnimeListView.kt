package com.joaovicttors.anime.presentation.features.anime_list.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaovicttors.anime.databinding.FragmentAnimeListBinding
import com.joaovicttors.anime.presentation.features.anime_list.adapter.AnimeListAdapter
import com.joaovicttors.base.BaseFragment
import com.joaovicttors.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class AnimeListView : BaseFragment() {

    override val viewModel: AnimeListViewModel by inject()

    private val adapter: AnimeListAdapter by lazy { AnimeListAdapter() }

    private lateinit var binding: FragmentAnimeListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAnimeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewFactory()
        viewModel.getAnimeList()
        viewStateObserver()
    }

    private fun recyclerViewFactory() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun viewStateObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
}