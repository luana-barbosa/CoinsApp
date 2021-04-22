package com.example.favorites.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.commons.helpers.SharedPreference
import com.example.commons.model.Coin
import com.example.commons.viewModel.CoinViewModel
import com.example.details.details.DetailsCoin
import com.example.favorites.R
import kotlinx.android.synthetic.main.activity_coin_favorite.*


class CoinFavorite : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_favorite)
        sharedPreference = SharedPreference(this)
        val viewModel: CoinViewModel by viewModels()
        viewModel.init()
        viewModel.listCoin.observe(this, Observer {
            setAdapter(createListFavorite(it))
            button_main_favorite.setOnClickListener { onClick(it) }

        })
    }

    private fun setAdapter(coin: List<Coin>?) {
        list_recycler_favorite.layoutManager = GridLayoutManager(this@CoinFavorite, 2)
        list_recycler_favorite.adapter = coin?.let {
            FavoriteAdapter(
                coin,
                this
            )
        }
    }

    fun clickCoin(coin: Coin) {
        val intent = Intent(this, DetailsCoin::class.java)
        intent.putExtra("coin", coin)
        startActivity(intent)
        finish()
    }

    private fun createListFavorite(list: List<Coin>): MutableList<Coin> {
        var listCoinFavorite: MutableList<Coin> = arrayListOf()
        for (element in list) {
            if (sharedPreference.getBoolean(element.assetId.toString())) {
                listCoinFavorite.add(element)
            }
        }
        return listCoinFavorite
    }

    fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_main_favorite) {
            val intent = Intent()
            intent.setClassName(this, "com.example.becamobile03android_squad2.view.MainActivity")
            startActivity(intent)
            finish()
        }
    }
}



