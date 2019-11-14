package com.baymax.quotesapplication.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baymax.quotesapplication.data.db.entity.Quote
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreSource (){

    private val firestore:FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val quoteList = mutableListOf<Quote>()
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quoteList
    }

    fun addQuotes(quote:Quote)
    {
        val map:HashMap<String,String> = HashMap<String,String>()
        map.put("Quote",quote.quote)
        map.put("Author",quote.author)
        firestore.collection("Quotes").add(map as Map<String, Any>).addOnCompleteListener {
            quoteList.add(quote)
            quotes.value = quoteList
        }
    }

    fun getQuotes(): LiveData<List<Quote>>
    {
        firestore.collection("Quotes").get().addOnCompleteListener { task ->
            if (task.isSuccessful)
            {
                quoteList.clear()
                for (quote in task.result!!)
                {
                    quoteList.add(Quote(quote.get("Quote").toString(),quote.get("Author").toString()))
                }
                Log.d("EVENT","Live Data is updated")
            }
        }
            .addOnCompleteListener {
                quotes.value = quoteList
            }
        return quotes
    }

}