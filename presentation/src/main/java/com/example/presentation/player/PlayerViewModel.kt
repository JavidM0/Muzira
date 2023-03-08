package com.example.presentation.player

import androidx.lifecycle.viewModelScope
import com.example.data.remote.music.contracts.MusicResponse
import com.example.domain.repository.MusicRepository
import com.example.ui_kit.`ui-kit`.viewmodel.noReplyFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class PlayerViewModel(private val musicRepository: MusicRepository) : PlayerViewModelApi() {

    override val successGetListEvent: MutableSharedFlow<List<MusicResponse>> = noReplyFlow()

    override fun showList() {
        viewModelScope.launch {
            val musicList = musicRepository.getMusicList()
            successGetListEvent.emit(musicList)
        }
    }
}
