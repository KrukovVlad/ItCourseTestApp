package com.example.itcoursetestapp.di

import com.example.itcoursetestapp.domain.auth.FetchOkUrlUseCase
import com.example.itcoursetestapp.domain.auth.FetchVkUrlUseCase
import com.example.itcoursetestapp.domain.auth.ValidateEmailUseCase
import com.example.itcoursetestapp.domain.auth.ValidatePasswordUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchVkUrlUseCase(get()) }
    factory { FetchOkUrlUseCase(get()) }
    factory { ValidateEmailUseCase() }
    factory { ValidatePasswordUseCase() }
}