package com.sporksoft.surfscrib.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResult(
        val photos: Photos?,
        val stat: String?
)

@JsonClass(generateAdapter = true)
data class Photos(
        val page: Int,
        val pages: String,
        val perpage: Int,
        val total: String,
        val photo: List<Photo>?
)

@JsonClass(generateAdapter = true)
data class Photo(
        val id: String,
        val owner: String,
        val secret: String,
        val server: String,
        val farm: Int,
        val title: String,
        val ispublic: Int,
        val isfriend: Int,
        val isfamily: Int,
        @Json(name = "url_s") val thumbUrl: String?,
        val height_s: String,
        val width_s: String
)
