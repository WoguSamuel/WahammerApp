package com.example.warhammer40kapp.utils

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import com.example.warhammer40kapp.model.cache.CacheCard
import com.google.gson.Gson

class WarhammerCardNavType : NavType<CacheCard>(isNullableAllowed = false) {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun get(bundle: Bundle, key: String): CacheCard? {
        return bundle.getParcelable(key, CacheCard::class.java)
    }

    override fun parseValue(value: String): CacheCard {
        return Gson().fromJson(value, CacheCard::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CacheCard) {
        bundle.putParcelable(key, value)
    }
}
