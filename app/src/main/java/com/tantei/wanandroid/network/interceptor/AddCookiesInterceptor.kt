package com.tantei.wanandroid.network.interceptor

import android.content.Context
import com.tantei.wanandroid.WanAndroidApplication
import com.tantei.wanandroid.utils.LLog
import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val sharedPreferences = WanAndroidApplication.context.getSharedPreferences("cookieData", Context.MODE_PRIVATE)
        val cookies = sharedPreferences.getStringSet("cookies", emptySet())
        if (!cookies.isNullOrEmpty()) {
            for (cookie in cookies) {
                builder.addHeader("Cookie", cookie)
            }
        }
        return chain.proceed(builder.build())
    }
}