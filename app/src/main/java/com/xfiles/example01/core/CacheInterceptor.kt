package com.xfiles.example01.core

import com.xfiles.example01.application.AppConstants
import okhttp3.*
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxStale(10, TimeUnit.HOURS)
            .onlyIfCached()
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}

class ForceCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!isInternetAvailable()) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}

fun isInternetAvailable(): Boolean {
    try {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    } catch (e: Exception) {
        return false
    }
}

class okHttp {
    fun okHttpClient() : OkHttpClient {
        val cacheSize = AppConstants.CACHE_SIZE.toLong()
        val localCache = Cache(AppConstants.CACHE_DIR, cacheSize)
        return OkHttpClient().newBuilder()
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor())
            .cache(localCache)
            .build()

    }
}