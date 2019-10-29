package com.baymax.quotesapplication.ui

import androidx.lifecycle.ViewModel
import com.baymax.quotesapplication.data.QuoteRepository
import com.baymax.quotesapplication.data.Quotes

class QuoteViewModel(private val quoteRepository: QuoteRepository): ViewModel() {
    fun getQuotes() = quoteRepository.getQuotes()
    fun addQuotes(quote: Quotes) = quoteRepository.addQuotes(quote);
}