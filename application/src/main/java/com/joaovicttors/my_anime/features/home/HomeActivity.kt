package com.joaovicttors.my_anime.features.home

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaovicttors.domain.entities.Anime
import com.joaovicttors.my_anime.R
import com.joaovicttors.my_anime.core.InfiniteScroll
import com.joaovicttors.my_anime.core.RecyclerViewInterface
import com.joaovicttors.my_anime.core.bases.BaseActivity
import com.joaovicttors.my_anime.core.extensions.bind
import com.joaovicttors.my_anime.core.extensions.setupToolbar
import com.joaovicttors.my_anime.databinding.ActivityHomeBinding
import com.joaovicttors.my_anime.features.detail.DetailFragment
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity(), RecyclerViewInterface {

    override val viewModel: HomeViewModel by inject()

    private lateinit var adapter: HomeAdapter
    private lateinit var dataBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        buildViewModelBindings()
        buildRecycleViewFactory()

        setupToolbar(dataBinding.toolbar,"My Anime", false)

        viewModel.retrieveAnimeList()
    }

    override fun onBackPressed() {
        supportFragmentManager.backStackEntryCount.also { count ->
            if (count == 0) super.onBackPressed() else supportFragmentManager.popBackStack()
        }
    }

    override fun onItemPressed(anime: Anime) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<DetailFragment>(dataBinding.fragmentContainer.id, args = bundleOf("anime_id" to anime.id))
        }
    }

    override fun onFavoritePressed(anime: Anime) {
        TODO("Not yet implemented")
    }

    private fun buildViewModelBindings() {
        bind(viewModel.success) { anime -> adapter.addItem(anime) }
    }

    private fun buildRecycleViewFactory() {
        adapter = HomeAdapter(this)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        dataBinding.recycleView.adapter = adapter
        dataBinding.recycleView.layoutManager = layoutManager

        dataBinding.recycleView.addOnScrollListener(InfiniteScroll(layoutManager) { viewModel.retrieveAnimeList() })
    }
}