package com.ruslanlialko.achievegoals

import android.app.Application
import com.ruslanlialko.achievegoals.di.dataModule
import com.ruslanlialko.achievegoals.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AchieveGoalsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AchieveGoalsApp)
            modules(listOf(dataModule, presentationModule))
        }
    }
}