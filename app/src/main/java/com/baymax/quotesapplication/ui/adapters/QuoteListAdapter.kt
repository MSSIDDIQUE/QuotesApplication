package com.baymax.quotesapplication.ui.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baymax.quotesapplication.R
import com.baymax.quotesapplication.data.Quotes
import com.baymax.quotesapplication.utilities.inflate
import kotlinx.android.synthetic.main.quote_row_item.view.*

class QuoteListAdapter(private val quotes:ArrayList<Quotes>):RecyclerView.Adapter<QuoteListAdapter.QuoteHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteHolder {

        val inflatedView = parent.inflate(R.layout.quote_row_item, false)
        return QuoteHolder(inflatedView)
    }


    override fun getItemCount(): Int {
        Log.d("###","toatat no of items in the list is "+quotes.size)
        return quotes.size
    }

    override fun onBindViewHolder(holder: QuoteHolder, position: Int) {
        val quote = quotes[position]
        holder.bindQuote(quote)
    }

    class QuoteHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object{
            private val Quote_Key = "QUOTE"
        }

        fun bindQuote(quote:Quotes)
        {
            itemView.Quote.setText(quote.quote)
            itemView.Author.setText(quote.author)
        }

    }
}