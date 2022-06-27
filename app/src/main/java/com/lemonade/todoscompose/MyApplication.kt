package com.lemonade.todoscompose

import android.app.Application
import com.lemonade.todoscompose.di.koinModule
import com.lemonade.todoscompose.infra.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(koinModule)
        }
        AppDatabase.setupDatabase(applicationContext)
    }
}