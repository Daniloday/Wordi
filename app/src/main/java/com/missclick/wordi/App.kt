package com.missclick.wordi

import android.app.Application

import com.missclick.wordi.di.appModule
import com.missclick.wordi.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(appModule, dataModule)
        }

    }

}