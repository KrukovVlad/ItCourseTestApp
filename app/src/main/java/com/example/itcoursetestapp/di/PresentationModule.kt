package com.example.itcoursetestapp.di

import com.example.itcoursetestapp.domain.core.browser.BrowserOpener
import com.example.itcoursetestapp.domain.core.DispatcherProvider
import com.example.itcoursetestapp.MainViewModel
import com.example.itcoursetestapp.presentation.auth.AuthViewModel
import com.example.itcoursetestapp.presentation.core.browser.BrowserOpenerImpl
import com.example.itcoursetestapp.presentation.core.DispatcherProviderImpl
import com.example.itcoursetestapp.presentation.home.HomeViewModel
import com.example.itcoursetestapp.presentation.main.MainContainerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }
    factory<BrowserOpener> { BrowserOpenerImpl(get()) }
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::MainContainerViewModel)
    viewModelOf(::MainViewModel)
}