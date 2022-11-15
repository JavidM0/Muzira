package com.example.muzira.di

import com.example.muzira.presentation.signin.SignInViewModel
import com.example.muzira.presentation.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        SignUpViewModel(registerUserUseCase = get())
    }

    viewModel {
        SignInViewModel(loginUserUseCase = get())
    }
}