package com.example.ui.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithLoading(
    exception: ((Throwable) -> Unit)? = null,
    loading: MutableStateFlow<Boolean>? = null,
    block: suspend CoroutineScope.() -> Unit,
): Job {
    val wrappedBlock: suspend CoroutineScope.() -> Unit = {
        loading?.emit(true)
        block(this)
        loading?.emit(false)
    }
    return launch(CoroutineExceptionHandler { _, throwable ->
        exception?.let { it(throwable) }
        loading?.tryEmit(false)
    }) {
        wrappedBlock()
    }
}

fun <T : Any> Flow<T>.bind(
    lifecycleOwner: LifecycleOwner,
    onNext: suspend (T) -> Unit = {},
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect(onNext)
        }
    }
}

fun <T> noReply(): MutableSharedFlow<T> =
    MutableSharedFlow(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
