package com.example.muzira.di

import androidx.room.Room
import com.example.data.remote.music.MusicApi
import com.example.data.room.UserDatabase
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    single {
        get<UserDatabase>().userDao()
    }

    single<MusicApi> {
        provideApi(retrofit = get())
    }
}

inline fun <reified Api> provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
