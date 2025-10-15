package com.example.ucourse

import android.app.Application
import com.example.ucourse.di.app
import com.example.ucourse.di.modules.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.core.context.startKoin

class UCourseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() = startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@UCourseApp)
        modules(app)
    }
}