package com.joaovicttors.my_anime.core.bases

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent

abstract class BaseViewModel: ViewModel(), LifecycleObserver, KoinComponent {
    val error: LiveData<String> get() = _error
    protected val _error: MutableLiveData<String> = MutableLiveData()

    val loading: LiveData<Boolean> get() = _loading
    protected val _loading: MutableLiveData<Boolean> = MutableLiveData()

    private val viewModelJob: Job = Job()
    private val viewModelScope = CoroutineScope((Dispatchers.Main + viewModelJob))

    fun launchData(func: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loading.value = true
                func()
            } catch (error: Exception) {
                _error.value = error.message
            }  finally {
                _loading.value = false
            }
        }
    }
}