package com.tantei.wanandroid.network.interceptor

import android.app.Application
import android.content.Context
import com.tantei.wanandroid.WanAndroidApplication
import com.tantei.wanandroid.utils.LLog
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val setCookie = response.headers("Set-Cookie")
        val isLoginCookie = !setCookie.find { it.startsWith("token_pass") }.isNullOrBlank()
        if (isLoginCookie) {
            val sharedPreferences = WanAndroidApplication.context.getSharedPreferences("cookieData", Context.MODE_PRIVATE)
            val cookies = HashSet<String>()
            if (setCookie.isNotEmpty()) {
                for (cookie in setCookie) {
                    LLog.d("cookie $cookie")
                    cookies?.add(cookie)
                }
            }
            val editor = sharedPreferences.edit()
            editor.putStringSet("cookies", cookies)
            editor.commit()
        }

        return response
    }
}