package com.example.cardgame

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

/**
 *
 * @author Ethan on 2021-07-13.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidFileProperties()

            modules(listOf(
                appContext,
                viewModelModule
            ))
        }
    }
}