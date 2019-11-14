package com.baymax.quotesapplication.data

data class Quotes (val quote:String,
                  val author:String) {
    override fun toString(): String {
        return "$quote - $author"
    }
}