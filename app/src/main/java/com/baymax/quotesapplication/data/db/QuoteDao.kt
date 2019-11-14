package com.baymax.quotesapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.baymax.quotesapplication.data.db.entity.Quote


@Dao
interface QuoteDao {
    @Insert
    fun addQuote(quote:Quote)

    @Insert
    fun addAllQuotes(list:List<Quote>)

    @Query("SELECT * FROM quote")
    fun getQuotes():LiveData<List<Quote>>

    @Query("DELETE FROM quote")
    fun deleteAllQuotes()
}