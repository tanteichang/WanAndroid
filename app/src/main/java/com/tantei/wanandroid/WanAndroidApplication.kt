package com.tantei.wanandroid

import android.app.Application
import android.content.Context
import com.tantei.wanandroid.repositories.ArticleRepository
import com.tantei.wanandroid.utils.LLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WanAndroidApplication : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        ArticleRepository.initialize(context)

        // log
        LLog.setDebuggable(BuildConfig.DEBUG)

    }
}