package com.flickr.app.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S>(intialState: S) : ViewModel() {

    private var mutableState = MutableStateFlow(intialState)
    var state: StateFlow<S> = mutableState

    fun setState(reducer: S.() -> S) {
        mutableState.value = reducer(mutableState.value)
    }
}