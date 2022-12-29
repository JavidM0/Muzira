package com.example.muzira.di

import com.example.presentation.signin.SignInViewModel
import com.example.presentation.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        SignUpViewModel(registerUserUseCase = get())
    }

    viewModel {
        SignInViewModel(loginUserUseCase = get())
    }
}