package com.baymax.quotesapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuoteDao {
    private val quoteList = mutableListOf<Quotes>();
    private val quotes = MutableLiveData<List<Quotes>>();

    init {
        quotes.value = quoteList
    }

    fun addQuotes(q:Quotes)
    {
        quoteList.add(q)
        quotes.value = quoteList
    }

    // Casting MutableLiveData to LiveData because its value
    // shouldn't be changed from other classes
    fun getQuotes() = quotes as LiveData<List<Quotes>>
}