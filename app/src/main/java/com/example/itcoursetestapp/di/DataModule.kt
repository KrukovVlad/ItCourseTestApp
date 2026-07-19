package com.example.itcoursetestapp.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.itcoursetestapp.data.auth.AuthSessionRepositoryImpl
import com.example.itcoursetestapp.data.auth.Base64EmailEncoderImpl
import com.example.itcoursetestapp.data.socials.OkDataSource
import com.example.itcoursetestapp.data.socials.SocialsRepositoryImpl
import com.example.itcoursetestapp.data.socials.VkDataSource
import com.example.itcoursetestapp.domain.auth.session.AuthSessionRepository
import com.example.itcoursetestapp.domain.auth.session.EmailEncoder
import com.example.itcoursetestapp.domain.socials.SocialsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val AUTH_PREFERENCES_NAME = "auth_prefs"

val dataModule = module {
    single { VkDataSource() }
    single { OkDataSource() }
    single<SocialsRepository> { SocialsRepositoryImpl(get(), get()) }

    single { 
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile(AUTH_PREFERENCES_NAME) }
        ) 
    }
    single<EmailEncoder> { Base64EmailEncoderImpl() }
    single<AuthSessionRepository> { AuthSessionRepositoryImpl(get(), get()) }

}