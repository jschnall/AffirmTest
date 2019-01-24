package com.sporksoft.surfscrib.web

import android.util.Log
import androidx.lifecycle.LiveData
import com.sporksoft.surfscrib.BuildConfig
import com.sporksoft.surfscrib.data.Photo
import com.sporksoft.surfscrib.data.SearchResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import androidx.lifecycle.MutableLiveData



object WebServiceManager {
    val LOGTAG = WebServiceManager.javaClass.simpleName
    const val BASE_URL = "https://api.flickr.com"
    private var webService: WebService? = null

    private fun buildMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
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

    fun getWebService(): WebService {
        if (webService == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(buildOkHttpClient())
                    .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
                    .build()

            webService = retrofit.create(WebService::class.java)
        }

        return webService!!
    }

    fun releaseWebService() {
        webService = null
    }

    fun fetchFeedItems(liveData: MutableLiveData<List<Photo>>, page: Int = 1, query: String? = "") {
        var pageStr = page.toString()
        val request: Call<SearchResult> = if (query.isNullOrEmpty()) {
            getWebService().recentPhotos(pageStr)
        } else {
            getWebService().searchPhotos(query, pageStr)
        }
        request.enqueue(object : Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>?, t: Throwable?) {
                Log.e(LOGTAG, t.toString())
            }

            override fun onResponse(call: Call<SearchResult>?, response: Response<SearchResult>?) {
                if (response != null) {
                    val result = response.body()
                    if (response.isSuccessful && result != null && result.photos != null) {
                        result.photos.photo?.let {
                            liveData.postValue(it)
                        }
                    } else {
                        val str = response.errorBody().toString()
                        Log.e(LOGTAG, str)
                    }
                }
            }
        })
    }
}