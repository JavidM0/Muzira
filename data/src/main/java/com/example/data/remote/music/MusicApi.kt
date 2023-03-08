package com.example.data.remote.music

import com.example.data.remote.music.contracts.MusicResponse
import retrofit2.Response
import retrofit2.http.GET

interface MusicApi {

    @GET("playlist/3nS8d7ekVjFLM4jVyqbDGY&rapidapi-key=d325d88d8emsh3d24da9250e9f3ep1567c1jsn5654a9b8ee38")
    fun getMusicList(): Response<List<MusicResponse>>
}
