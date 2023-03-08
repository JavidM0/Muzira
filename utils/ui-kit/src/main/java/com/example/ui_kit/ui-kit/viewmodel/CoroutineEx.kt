package com.example.ui_kit.`ui-kit`.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

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

fun <T> noReplyFlow(): MutableSharedFlow<T> = MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
