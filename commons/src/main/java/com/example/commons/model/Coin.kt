package com.example.commons.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Coin(
    @SerializedName("asset_id") var assetId: String,
    @SerializedName("name") var name: String,
    @SerializedName("type_is_crypto") var typeCrypto: String,
    @SerializedName("volume_1hrs_usd") var volumeHour: String,
    @SerializedName("volume_1day_usd") var volumeDay: String,
    @SerializedName("volume_1mth_usd") var volumeMonth: String,
    @SerializedName("id_icon") var idIcon: String?,
    @SerializedName("price_usd") var priceUsd: String?,
    var favorites: Boolean
) : Parcelable
