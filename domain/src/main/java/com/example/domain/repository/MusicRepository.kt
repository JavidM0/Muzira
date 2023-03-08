package com.example.domain.repository

import com.example.data.remote.music.MusicApi

class MusicRepository(private val api: MusicApi) {

    suspend fun getMusicList() = api.getMusicList()
}
