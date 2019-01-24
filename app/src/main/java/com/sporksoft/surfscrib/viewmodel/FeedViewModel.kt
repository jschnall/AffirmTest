package com.sporksoft.surfscrib.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sporksoft.surfscrib.data.Photo
import com.sporksoft.surfscrib.web.WebService
import com.sporksoft.surfscrib.web.WebServiceManager


private const val START_PAGE = 1


class FeedViewModel : ViewModel() {
    private val repository = WebServiceManager
    private val page = MutableLiveData<Int>()
    private val query = MutableLiveData<String>()
    private val _items = MutableLiveData<List<Photo>>()

    val items: LiveData<List<Photo>>
        get() = _items

    init {
        query.value = ""
        updateItems(true)
    }

    fun getPage() = page
    fun getQuery() = query

    fun updateItems(reset: Boolean = false) {
        if (reset) page.value = START_PAGE
        repository.fetchFeedItems(_items,page.value ?: START_PAGE, query.value)
    }

    fun updatePage(page: Int) {
        this.page.value = page
        updateItems()
    }

    fun updateQuery(query: String) {
        this.query.value = query
        updateItems()
    }
}
