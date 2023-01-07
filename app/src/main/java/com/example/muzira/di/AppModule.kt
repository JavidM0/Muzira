package com.example.muzira.di

import org.koin.dsl.module

val appModule = module {
    dataModule +
            domainModule +
            presentationModule
}
