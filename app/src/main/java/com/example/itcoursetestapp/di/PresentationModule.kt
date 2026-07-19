package com.example.itcoursetestapp.di

import com.example.itcoursetestapp.domain.auth.BrowserOpener
import com.example.itcoursetestapp.domain.core.DispatcherProvider
import com.example.itcoursetestapp.presentation.auth.AuthViewModel
import com.example.itcoursetestapp.presentation.auth.BrowserOpenerImpl
import com.example.itcoursetestapp.presentation.core.DispatcherProviderImpl
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
    factory<BrowserOpener> { BrowserOpenerImpl(get()) }
    viewModelOf(::AuthViewModel)
}