package com.igorgiroti.login_sample

import android.app.Application
import com.igorgiroti.login_sample.di.LoginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                LoginModule.loginModule
            )
        }
    }
}
