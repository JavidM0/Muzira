package com.example.muzira.di

import androidx.room.Room
import com.example.data.mapper.UserMapper
import com.example.data.repository.UserRepositoryImpl
import com.example.data.room.UserDatabase
import org.koin.dsl.module

val dataModule = module {

    factory {
        UserMapper()
    }

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

    single {
        UserRepositoryImpl(userDao = get(), userMapper = get())
    }
}