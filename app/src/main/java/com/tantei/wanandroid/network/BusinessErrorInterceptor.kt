package com.tantei.wanandroid.network

import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets.UTF_8
import org.json.JSONObject
class BusinessErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            return response
        }

        val responseBody= response.body()!!
        val source = responseBody.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer
        val contentType = responseBody.contentType()
        val charset: Charset = contentType?.charset(UTF_8) ?: UTF_8
        val resultString = buffer.clone().readString(charset)

        val jsonObject = JSONObject(resultString)
        if (!jsonObject.has("errorCode")) {
            return response
        }

        val errorCode = jsonObject.optInt("errorCode")
        if (errorCode == CODE.OK.code) {
            return response
        }
        throw ApiException(errorCode, "some error message")
    }
}