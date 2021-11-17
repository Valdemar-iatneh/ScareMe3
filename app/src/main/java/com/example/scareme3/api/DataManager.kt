package com.example.scareme3.api

import android.content.Context
import android.content.SharedPreferences

class DataManager(private val baseContext: Context) {
    val shared: SharedPreferences = baseContext.getSharedPreferences("WS", Context.MODE_PRIVATE)

    fun getToken() = shared.getString("token", "")
    fun setToken(value: String) = shared.edit().putString("token", value).apply()

    fun getEmail() = shared.getString("email", "")
    fun setEmail(value: String) = shared.edit().putString("email", value).apply()

    fun getPassword() = shared.getString("password", "")
    fun setPassword(value: String) = shared.edit().putString("password", value).apply()

    val api = Api.createApi()
}