package com.example.becamobile03android_squad2.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.becamobile03android_squad2.R
import com.example.details.details.DetailsCoin
import com.example.favorites.favorite.CoinFavorite
import com.example.commons.helpers.CoinDate
import com.example.commons.model.Coin
import com.example.commons.viewModel.CoinViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val coinData = CoinDate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: CoinViewModel by viewModels()
        viewModel.init()
        viewModel.listCoin.observe(this, Observer {
            setAdapter(it)
            searchListDisplay(it)

        }
        )

        val date: TextView = findViewById(R.id.data_coin_top_bar)
        date.text = coinData.callDate()
        date.contentDescription = coinData.callDate()

        button_main.setOnClickListener {
            onClick(it)
        }
        button_star.setOnClickListener { onClick(it) }
    }

    private fun setAdapter(coin: List<Coin>?) {
        list_recycler_coin.layoutManager = GridLayoutManager(this@MainActivity, 1)
        list_recycler_coin.adapter = coin?.let {
            CoinAdapter(
                coin,
                this,
                this
            )
        }
    }

    fun clickCoin(coin: Coin) {
        val intent = Intent(this, DetailsCoin::class.java)
        intent.putExtra("coin", coin)
        startActivity(intent)
    }

    private fun resultListSearch(search: String, list: List<Coin>) {
        var lisResultSearch: MutableList<Coin> = arrayListOf()
        for (element in list) {
            if (element.name != null) {
                if (element.name!!.contains(search, ignoreCase = true)) {
                    lisResultSearch.add(element)
                }
            }
        }
        setAdapter(lisResultSearch)
    }

    private fun searchListDisplay(list: List<Coin>) {
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                resultListSearch(query, list)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                resultListSearch(newText, list)
                return false
            }
        })
    }

     fun onClick(view: View) {
        val id = view.id

        when {
            (id == R.id.button_main) -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            (id == R.id.button_star) -> {
                val intentFavorite = Intent(this, CoinFavorite::class.java)
                startActivity(intentFavorite)
            }
        }
    }
}