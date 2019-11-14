package com.baymax.quotesapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baymax.quotesapplication.data.db.entity.Quote
import java.security.AccessControlContext

@Database(
    entities = [Quote::class],
    version = 1
)
abstract class QuotesDatabase:RoomDatabase()
{
    abstract fun getQuoteDao():QuoteDao

    companion object
    {
        @Volatile private var instance : QuotesDatabase?=null
        private val lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(lock)
        {
            instance?:buildDatabase(context).also{
                 instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,QuotesDatabase::class.java,"QuotsDatabase").build()
    }
}