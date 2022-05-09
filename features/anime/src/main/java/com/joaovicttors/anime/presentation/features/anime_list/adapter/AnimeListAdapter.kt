package com.joaovicttors.anime.presentation.features.anime_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.anime.R
import com.joaovicttors.anime.domain.entities.Anime

class AnimeListAdapter(
    private val onAnimeClicked: (Anime) -> Unit
) : RecyclerView.Adapter<AnimeListItemViewHolder>() {

    var animeList: List<Anime> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value.sortedByDescending { it.score }
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: AnimeListItemViewHolder, position: Int) {
        holder.bind(animeList[position], onAnimeClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListItemViewHolder {
        return AnimeListItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.adapter_anime_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}