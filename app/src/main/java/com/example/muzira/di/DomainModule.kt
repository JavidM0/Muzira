package com.example.muzira.di

import com.example.domain.repository.MusicRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.LoginUserUseCase
import com.example.domain.usecase.RegisterUserUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        RegisterUserUseCase(userRepository = get())
    }

    factory {
        LoginUserUseCase(userRepository = get())
    }

    single {
        UserRepository(userDao = get())
    }

    single {
        MusicRepository(api = get())
    }
}
