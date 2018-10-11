package com.sporksoft.affirm.web

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sporksoft.affirm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

object WebServiceManager {
    const val BASE_URL = "https://api.flickr.com"
    private var webService: WebService? = null

    private fun buildGson(): Gson {
        return GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create()
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(interceptor)
        }

        return builder.build()
    }

    fun instanceOf(): WebService? {
        if (webService == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(buildOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(buildGson()))
                    .build()

            webService = retrofit.create(WebService::class.java)
        }

        return webService
    }

    fun releaseWebService() {
        webService = null
    }
}