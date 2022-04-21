package com.joaovicttors.my_anime.features.home

import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.my_anime.R
import com.joaovicttors.my_anime.core.RecyclerViewInterface
import com.joaovicttors.my_anime.core.bases.BaseAdapter

class HomeAdapter(
    recyclerViewInterface: RecyclerViewInterface
) : BaseAdapter<Anime>(R.layout.adapter_home, recyclerViewInterface)