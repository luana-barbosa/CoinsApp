package com.example.details.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.commons.helpers.SharedPreference
import com.example.commons.model.Coin
import com.example.details.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_coin.*


class DetailsCoin : AppCompatActivity() {

    private var coin: Coin? = null
    private lateinit var shardPreference: SharedPreference
    private var iconId: String? = ""
    private var priceId: String? = ""
    private var hourId: String? = ""
    private var dayId: String? = ""
    private var monthId: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_coin)

        shardPreference = SharedPreference(this)

        getExtra()
        back_button.setOnClickListener {
            clickButtonBack(it)
        }

        button_favorite.setOnClickListener { onClikStatusCoin(it) }
        checkButton()
    }

    private fun getExtra() {
        if (intent.extras != null) {
            val coin = intent.getParcelableExtra("coin") as? Coin
            iconId = coin?.assetId
            priceId = coin?.priceUsd
            hourId = coin?.volumeHour
            dayId = coin?.volumeDay
            monthId = coin?.volumeMonth

            id_coin.text = coin?.assetId
            price_day.text = coin?.priceUsd ?: "00.00"
            volume_1hrs_usd.text = coin?.volumeHour
            volume_1day_usd.text = coin?.volumeDay
            volume_1mth_usd.text = coin?.volumeMonth

            val image = coin?.idIcon?.replace("-", "")
            Picasso.get()
                .load("https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_32/${image}.png")
                .placeholder(R.mipmap.details_ic_launcher_round)
                .into(id_icon)
            if (coin != null) {
                if (coin.assetId?.let { shardPreference.getBoolean(it) } == true) {
                    favorite_star.visibility = View.VISIBLE
                } else if (!coin.assetId?.let { shardPreference.getBoolean(it) }!!) {
                    favorite_star.visibility = View.GONE
                }
            }
        }
    }

    private fun clickButtonBack(view: View) {
        val page = back_button.id
        if (page == view.id) {
            val intent = Intent()
            intent.setClassName(this, "com.example.becamobile03android_squad2.view.MainActivity")
            startActivity(intent)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClikStatusCoin(view: View) {

        if (button_favorite.text == "ADICIONAR") {
            shardPreference.storeBoolean(iconId.toString(), true)
            checkButton()
            coin?.favorites = false

        } else if (button_favorite.text == "REMOVER") {
            shardPreference.storeBoolean(iconId.toString(), false)
            checkButton()
            coin?.favorites = true
        }
    }

    private fun checkButton() {
        if (shardPreference.getBoolean(iconId.toString())) {
            button_favorite.text = "REMOVER"
            favorite_star.visibility = View.VISIBLE

        } else if (!shardPreference.getBoolean(iconId.toString())) {
            button_favorite.text = "ADICIONAR"
            favorite_star.visibility = View.GONE

        }
    }
}