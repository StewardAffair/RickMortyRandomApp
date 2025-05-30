package com.roman.rickmortyrandomapp.presentation

import android.app.Application
import com.roman.rickmortyrandomapp.appModule
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}