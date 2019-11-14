package com.baymax.quotesapplication.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.baymax.quotesapplication.R
import com.baymax.quotesapplication.data.Quotes
import com.baymax.quotesapplication.data.db.entity.Quote
import com.baymax.quotesapplication.ui.adapters.QuoteListAdapter
import com.baymax.quotesapplication.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.content_main.*

class QuoteActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter : QuoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeUi()
    }
    private fun initializeUi(){
        val factory = InjectorUtils.provideQuoteViewModelFactory(this)
        val viewModel = ViewModelProvider(this,factory).get(QuoteViewModel::class.java)
        viewModel.getQuotes().observe(this, Observer {
            quotes->
            adapter = QuoteListAdapter(quotes as ArrayList<Quote>)
            Log.d("###","Adapter is set on list ")
            linearLayoutManager = LinearLayoutManager(this)
            recycler_view.layoutManager = linearLayoutManager
            recycler_view.adapter = adapter
        })
        fab.setOnClickListener { view ->
            val quote = Quote(edit_quote.text.toString(),"__"+edit_author.text.toString())
            viewModel.addQuotes(quote)
            Snackbar.make(view, "Your Quote is added to the list", Snackbar.LENGTH_LONG).show()
            edit_quote.setText("")
            edit_author.setText("")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
