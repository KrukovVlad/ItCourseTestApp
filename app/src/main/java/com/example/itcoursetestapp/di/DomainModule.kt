package com.example.itcoursetestapp.di

import com.example.itcoursetestapp.domain.auth.session.CheckUserSessionUseCase
import com.example.itcoursetestapp.domain.socials.FetchOkUrlUseCase
import com.example.itcoursetestapp.domain.socials.FetchVkUrlUseCase
import com.example.itcoursetestapp.domain.auth.session.SaveUserSessionUseCase
import com.example.itcoursetestapp.domain.auth.validation.ValidateEmailUseCase
import com.example.itcoursetestapp.domain.auth.validation.ValidatePasswordUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchVkUrlUseCase(get()) }
    factory { FetchOkUrlUseCase(get()) }
    factory { ValidateEmailUseCase() }
    factory { ValidatePasswordUseCase() }
    factory { SaveUserSessionUseCase(get()) }
    factory { CheckUserSessionUseCase(get()) }
}