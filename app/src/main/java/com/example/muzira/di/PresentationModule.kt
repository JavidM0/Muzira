package com.example.muzira.di

import com.example.presentation.signin.SignInViewModel
import com.example.presentation.signin.SignInViewModelApi
import com.example.presentation.signup.SignUpViewModel
import com.example.presentation.signup.SignUpViewModelApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<SignUpViewModelApi> {
        SignUpViewModel(registerUserUseCase = get())
    }

    viewModel<SignInViewModelApi> {
        SignInViewModel(loginUserUseCase = get())
    }
}
