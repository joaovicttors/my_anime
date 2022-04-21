package com.joaovicttors.my_anime.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.joaovicttors.my_anime.R
import com.joaovicttors.my_anime.core.bases.BaseFragment
import com.joaovicttors.my_anime.core.extensions.bind
import com.joaovicttors.my_anime.databinding.FragmentDetailBinding
import org.koin.core.component.inject

class DetailFragment : BaseFragment() {

    override val viewModel: DetailViewModel by inject()

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.retrieveSpecificAnime(requireArguments().getInt("anime_id"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentDetailBinding.inflate(inflater, container, false)

        buildViewModelBindings()
        setupActionBar()

        return dataBinding.root
    }

    private fun buildViewModelBindings() {
        bind(viewModel.success) { dataBinding.anime = it }
        bind(viewModel.loading) { dataBinding.loading = it }
    }

    private fun setupActionBar() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(dataBinding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = ""

        dataBinding.toolbar.setNavigationIcon(R.drawable.outline_close_white_18)
        dataBinding.toolbar.setNavigationOnClickListener { removeFragment() }

    }

    private fun removeFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(this)
            ?.commit()
    }
}