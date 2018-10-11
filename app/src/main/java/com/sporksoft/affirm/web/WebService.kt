package com.sporksoft.affirm.web

import com.sporksoft.affirm.models.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/services/rest/?method=flickr.photos.search&api_key=0781344d8d941350f67af237e42a8a43&extras=url_s&format=json&nojsoncallback=1&per_page=20")
    fun searchPhotos(@Query("text") query: String,
                     @Query("page") page: String): Call<SearchResult>

    @GET("/services/rest/?method=flickr.photos.getRecent&api_key=0781344d8d941350f67af237e42a8a43&extras=url_s&per_page=20&format=json&nojsoncallback=1")
    fun recentPhotos(@Query("page") page: String): Call<SearchResult>

}