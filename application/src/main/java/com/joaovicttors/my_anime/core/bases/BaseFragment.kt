package com.joaovicttors.my_anime.core.bases

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.core.component.KoinComponent

abstract class BaseFragment : Fragment(), KoinComponent {

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
    }
}