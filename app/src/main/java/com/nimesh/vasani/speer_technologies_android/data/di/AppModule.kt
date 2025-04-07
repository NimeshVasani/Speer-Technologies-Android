package com.nimesh.vasani.speer_technologies_android.data.di

import com.nimesh.vasani.speer_technologies_android.data.repositories.UsersRepository
import com.nimesh.vasani.speer_technologies_android.presentation.viewmodels.UsersViewmodel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


fun provideAppModule() = module {


    //provides auth
    viewModelOf(::UsersViewmodel)
    singleOf(::UsersRepository)
}