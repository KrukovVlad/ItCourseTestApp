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
private const val API_BASE_URL = "https://drive.usercontent.google.com/"
private const val ROOM_DB_NAME = "itcourse_db"

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
    single {
        retrofit2.Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()
            .create(com.example.itcoursetestapp.data.home.api.CourseApi::class.java)
    }
    single {
        androidx.room.Room.databaseBuilder(
            androidContext(),
            com.example.itcoursetestapp.data.home.db.AppDatabase::class.java,
            ROOM_DB_NAME
        ).build()
    }
    single { get<com.example.itcoursetestapp.data.home.db.AppDatabase>().courseDao() }
    single<com.example.itcoursetestapp.domain.home.CourseRepository> { 
        com.example.itcoursetestapp.data.home.CourseRepositoryImpl(get(), get()) 
    }
}