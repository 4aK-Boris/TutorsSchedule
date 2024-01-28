package dmitriy.losev.ui.core

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun BaseViewModel.runOnBackground(block: suspend () -> Unit): Job =
    viewModelScope.launch(Dispatchers.Default) {
        block()
    }

fun BaseViewModel.runOnBackgroundWithLoading(block: suspend () -> Unit): Job =
    viewModelScope.launch(Dispatchers.Default) {
        startLoading()
        block()
    }

fun BaseViewModel.runOnIO(block: suspend () -> Unit): Job =
    viewModelScope.launch(Dispatchers.IO) {
        block()
    }

fun BaseViewModel.runOnIOWithLoading(block: suspend () -> Unit): Job =
    viewModelScope.launch(Dispatchers.IO) {
        startLoading()
        block()
    }

fun BaseViewModel.runOnMain(block: suspend () -> Unit): Job =
    viewModelScope.launch(Dispatchers.Main) {
        block()
    }
