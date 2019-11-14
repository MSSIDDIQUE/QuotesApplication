package com.baymax.quotesapplication.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(  val quote: String,
                   val author:String) {
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0;
}