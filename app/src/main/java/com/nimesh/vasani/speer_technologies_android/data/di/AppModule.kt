package com.nimesh.vasani.speer_technologies_android.data.di

import com.nimesh.vasani.speer_technologies_android.data.repositories.AuthRepository
import com.nimesh.vasani.speer_technologies_android.presentation.viewmodels.AuthViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


fun provideAppModule() = module {


    //provides auth
    viewModelOf(::AuthViewModel)
    singleOf(::AuthRepository)
}