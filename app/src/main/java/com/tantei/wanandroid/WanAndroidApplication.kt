package com.tantei.wanandroid

import android.app.Application
import android.content.Context

class WanAndroidApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}