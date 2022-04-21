package com.joaovicttors.my_anime.core.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.component.KoinComponent

abstract class BaseActivity: AppCompatActivity(), KoinComponent {

    abstract val viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(viewModel)
    }
}