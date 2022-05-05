package com.joaovicttors.base

import androidx.fragment.app.Fragment
import org.koin.core.component.KoinComponent

abstract class BaseFragment : Fragment(), KoinComponent {

    abstract val viewModel: BaseViewModel
}