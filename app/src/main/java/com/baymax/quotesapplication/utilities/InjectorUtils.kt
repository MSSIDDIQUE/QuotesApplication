package com.baymax.quotesapplication.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.baymax.quotesapplication.data.FakeDatabase
import com.baymax.quotesapplication.data.FakeQuoteDao
import com.baymax.quotesapplication.data.FirestoreSource
import com.baymax.quotesapplication.data.QuoteRepository
import com.baymax.quotesapplication.data.db.QuotesDatabase
import com.baymax.quotesapplication.ui.QuoteViewModelFactory
import com.google.android.material.snackbar.Snackbar

object InjectorUtils {
    fun provideQuoteViewModelFactory(context: Context): QuoteViewModelFactory {
        val quoteRepository = QuoteRepository.getInstance(FirestoreSource(), QuotesDatabase.invoke(context),
            checkConnection(context))
        return QuoteViewModelFactory(quoteRepository)
    }

    fun checkConnection(context: Context):String
    {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if(isConnected)
        {
            return "ONLINE"
        }
        Toast.makeText(context,"You are offline now",Toast.LENGTH_LONG).show()
        return "OFFLINE"
    }
}