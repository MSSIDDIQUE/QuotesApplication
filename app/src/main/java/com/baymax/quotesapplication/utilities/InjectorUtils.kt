package com.baymax.quotesapplication.utilities

import com.baymax.quotesapplication.data.FakeDatabase
import com.baymax.quotesapplication.data.FakeQuoteDao
import com.baymax.quotesapplication.data.FirestoreSource
import com.baymax.quotesapplication.data.QuoteRepository
import com.baymax.quotesapplication.ui.QuoteViewModelFactory

object InjectorUtils {
    fun provideQuoteViewModelFactory(): QuoteViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FirestoreSource())
        return QuoteViewModelFactory(quoteRepository)
    }
}