package com.example.myapplication.Model

import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer "+ RestAPI.token)
            .addHeader("Connection", "close")
//            .addHeader("x-user-id", RestAPI.userId)
            .build()

        return chain.proceed(request)
    }
}