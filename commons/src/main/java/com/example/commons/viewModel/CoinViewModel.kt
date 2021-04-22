package com.example.commons.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.commons.api.CoinService

import com.example.commons.model.Coin
import retrofit2.Call
import retrofit2.Response

open class CoinViewModel : ViewModel() {
    val listCoinResult: MutableList<Coin> = arrayListOf()
    private val coinLiveData: MutableLiveData<List<Coin>> = MutableLiveData()
    val listCoin: MutableLiveData<List<Coin>>
        get() = coinLiveData

    fun init() {
        callListCoin()
    }

    private fun callListCoin() {
        val call = CoinService.coinRetrofitApi()
            .getAllList("CFB9107C-F454-4F93-B412-C7F15E3D284D")
        call.enqueue(object : retrofit2.Callback<List<Coin>> {
            override fun onResponse(call: Call<List<Coin>>, response: Response<List<Coin>>) {
                if (response.isSuccessful)
                    response.body()?.forEach {
                        listCoinResult.add(it)
                    }else {response.errorBody()?.let {
                    when(response.code()){
                        400 -> ("Bad request")
                        401 -> ("Unauthorized")
                        403 -> ("Forbidden")
                        429 -> ("too many requests")
                        550 -> ("No date ")
                        else -> ("Error")
                    }
                }

                }
                coinLiveData.postValue(listCoinResult)
            }

            override fun onFailure(call: Call<List<Coin>>, t: Throwable) {
                coinLiveData.postValue(null)
            }
        })
    }
}


