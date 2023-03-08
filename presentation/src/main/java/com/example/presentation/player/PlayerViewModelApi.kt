package com.example.presentation.player

import androidx.lifecycle.ViewModel
import com.example.data.remote.music.contracts.MusicResponse
import kotlinx.coroutines.flow.Flow

abstract class PlayerViewModelApi : ViewModel() {

    abstract val successGetListEvent: Flow<List<MusicResponse>>

    abstract fun showList()
}
