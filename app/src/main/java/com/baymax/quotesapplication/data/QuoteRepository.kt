package com.baymax.quotesapplication.data

class QuoteRepository private constructor(private val firestoreSource: FirestoreSource){

    fun addQuotes(quote:Quotes)
    {
        firestoreSource.addQuotes(quote)

    }

    fun getQuotes()=firestoreSource.getQuotes()

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(firestoreSource: FirestoreSource) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(firestoreSource).also { instance = it }
            }
    }
}