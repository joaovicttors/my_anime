package com.joaovicttors.anime.presentation.features.anime_list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.anime.databinding.AdapterAnimeListBinding
import com.joaovicttors.anime.domain.entities.Anime
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class AnimeListItemViewHolder(
    private val binding: AdapterAnimeListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(anime: Anime) {
        binding.anime = anime
        binding.executePendingBindings()

        // TODO joao.santana
        binding.shimmer.startShimmer()

        Picasso.get()
            .load(anime.coverImage)
            .into(binding.animeImage, object : Callback {
                override fun onSuccess() {
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.shimmer.stopShimmer()
                    binding.shimmer.visibility = View.GONE
                }
            })
    }
}