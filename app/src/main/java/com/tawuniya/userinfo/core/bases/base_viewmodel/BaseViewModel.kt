package com.tawuniya.userinfo.core.bases.base_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawuniya.userinfo.core.error.AppError
import com.tawuniya.userinfo.core.error.AppErrorHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


abstract class BaseViewModel<Intent : ViewIntent, State : ViewState>(initialState: State) :
    ViewModel(), AppErrorHandler {

    var uiState by mutableStateOf(initialState)
        private set

    private val _uiStateFlow = MutableStateFlow(initialState)
    val uiStateFlow = _uiStateFlow.asStateFlow()


    private val channel = Channel<Intent>(Channel.UNLIMITED)

    init {
        processIntent()
    }

    private fun processIntent() = viewModelScope.launch {
        channel.consumeAsFlow().collect { intent -> processIntent(intent) }
    }

    abstract fun processIntent(intent: Intent)


    protected fun updateStateOf(reducer: State.() -> State) {
        uiState = uiState.reducer()
    }


    protected fun updateStateFlow(reducer: State.() -> State) =
        _uiStateFlow.update(reducer)


    fun sendIntent(intent: Intent) = viewModelScope.launch { channel.send(intent) }


    override fun handleError(error: AppError, callback: AppError.() -> Unit) {
        error.logError()
        callback(error)
    }
}

interface ViewState

interface ViewIntent