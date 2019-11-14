package com.baymax.quotesapplication.data

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.baymax.quotesapplication.data.db.QuoteDao
import com.baymax.quotesapplication.data.db.QuotesDatabase
import com.baymax.quotesapplication.data.db.entity.Quote

class QuoteRepository private constructor(private val firestoreSource: FirestoreSource,
                                          private val database: QuotesDatabase,
                                          private val status:String){


    fun addQuotes(quote:Quote)
    {
        InsertQuoteAsyncTask(
            database.getQuoteDao()
        ).execute(quote)
        firestoreSource.addQuotes(quote)
    }

    fun addAllQuotes(quotes:List<Quote>)
    {
        InsertQuotesAsyncTask(
            database.getQuoteDao()
        ).execute(quotes)
    }

    fun getQuotes():LiveData<List<Quote>>
    {

        if(status.equals("ONLINE"))
        {
            deleteAllQuotes()
            return Transformations.switchMap(firestoreSource.getQuotes()) {
                addAllQuotes(it)
                Log.d("EVENT",it.isEmpty().toString())
                database.getQuoteDao().getQuotes()
            }
        }
        return database.getQuoteDao().getQuotes()
    }

    fun deleteAllQuotes() {
        DeleteAllQuotesAsyncTask(
            database.getQuoteDao()
        ).execute()
    }

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(firestoreSource: FirestoreSource,database: QuotesDatabase,status: String) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(firestoreSource,database,status).also { instance = it }
            }
    }

    private class InsertQuoteAsyncTask(val quoteDao: QuoteDao) : AsyncTask<Quote, Unit, Unit>() {

        override fun doInBackground(vararg quote: Quote?) {
            quoteDao.addQuote(quote[0]!!)
        }
    }

    private class InsertQuotesAsyncTask(val quoteDao: QuoteDao) : AsyncTask<List<Quote>, Unit, Unit>() {

        override fun doInBackground(vararg quote: List<Quote>?) {
            quoteDao.addAllQuotes(quote[0]!!)
            Log.d("EVENT","Data is saved to the database")
        }
    }

    private class DeleteAllQuotesAsyncTask(val quoteDao: QuoteDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            quoteDao.deleteAllQuotes()
        }
    }
}