package com.example.commons.helpers

import android.content.Context

class SharedPreference(context: Context) {
    private val mSharedPreferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)

    fun storeBoolean(key: String, Value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, Value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return mSharedPreferences.getBoolean(key, false) ?: false
    }
}