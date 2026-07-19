package com.example.itcoursetestapp.di

import com.example.itcoursetestapp.data.auth.OkDataSource
import com.example.itcoursetestapp.data.auth.SocialsRepositoryImpl
import com.example.itcoursetestapp.data.auth.VkDataSource
import com.example.itcoursetestapp.domain.auth.SocialsRepository
import org.koin.dsl.module

val dataModule = module {
    single { VkDataSource() }
    single { OkDataSource() }
    single<SocialsRepository> { SocialsRepositoryImpl(get(), get()) }
}