package com.macruware.horoscopeapp.data.core.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor{

    // chain es la petición que estamos haciendo, y recibiremos la response de retrofit
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header("Autorization",tokenManager.getToken()).build()
        return chain.proceed(request)
    }
}

class TokenManager @Inject constructor(){
    fun getToken():String = "uwvfbsdhbvc"
}

