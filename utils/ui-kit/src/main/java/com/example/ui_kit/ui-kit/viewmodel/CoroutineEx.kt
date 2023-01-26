package com.example.ui_kit.`ui-kit`.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.data.module.UserInfoModule
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

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

fun noReplyFlow(): MutableSharedFlow<UserInfoModule> = MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
