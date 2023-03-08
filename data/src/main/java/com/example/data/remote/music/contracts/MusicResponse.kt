package com.example.data.remote.music.contracts

import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("link") val link: String,
    @SerializedName("song_name") val songName: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("thumb") val image: String,
    @SerializedName("duration") val duration: String
)
