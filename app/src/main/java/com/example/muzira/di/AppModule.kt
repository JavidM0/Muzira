package com.example.muzira.di

import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {

    val appModule: List<Module> = dataModule +
            domainModule +
            presentationModule
}
