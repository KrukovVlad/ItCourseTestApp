package com.example.itcoursetestapp

import android.app.Application
import com.example.itcoursetestapp.di.dataModule
import com.example.itcoursetestapp.di.domainModule
import com.example.itcoursetestapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}