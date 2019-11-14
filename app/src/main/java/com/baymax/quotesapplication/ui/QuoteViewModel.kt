package com.baymax.quotesapplication.ui

import androidx.lifecycle.ViewModel
import com.baymax.quotesapplication.data.QuoteRepository
import com.baymax.quotesapplication.data.db.entity.Quote

class QuoteViewModel(private val quoteRepository: QuoteRepository): ViewModel() {
    fun getQuotes() = quoteRepository.getQuotes()
    fun addQuotes(quote: Quote) = quoteRepository.addQuotes(quote)
}