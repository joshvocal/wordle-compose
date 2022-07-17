package dev.joshvocal.wordlecompose.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<T>(
    initialState: T,
) : ViewModel() {
    private val _flowState = MutableStateFlow(initialState)

    val flowState: StateFlow<T>
        get() = _flowState

    val currentState: T
        get() = flowState.value

    fun updateState(newState: T.() -> T) {
        _flowState.value = newState(_flowState.value)
    }
}