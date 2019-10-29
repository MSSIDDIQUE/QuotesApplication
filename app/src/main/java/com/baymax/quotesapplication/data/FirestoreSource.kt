package com.baymax.quotesapplication.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreSource (){

    private val firestore:FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val quoteList = mutableListOf<Quotes>()
    private val quotes = MutableLiveData<List<Quotes>>()

    init {
        quotes.value = quoteList
    }

    fun addQuotes(quote:Quotes)
    {
        val map:HashMap<String,String> = HashMap<String,String>()
        map.put("Quote",quote.quote)
        map.put("Author",quote.author)
        firestore.collection("Quotes").add(map as Map<String, Any>).addOnCompleteListener {
            quoteList.add(quote)
            quotes.value = quoteList
        }
    }

    fun getQuotes(): LiveData<List<Quotes>>
    {
        firestore.collection("Quotes").get().addOnCompleteListener { task ->
            if (task.isSuccessful)
            {
                quoteList.clear()
                for (quote in task.result!!)
                {
                    quoteList.add(Quotes(quote.get("Quote").toString(),quote.get("Author").toString()))
                }
            }
        }
        quotes.value = quoteList
        Log.d("###","data is fetched"+quotes)
        return quotes
    }

}