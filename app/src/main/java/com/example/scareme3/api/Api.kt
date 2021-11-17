package com.example.scareme3.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface Api {

    companion object {
        fun createApi(): Api {
           val gson = GsonBuilder()
               .setLenient()
               .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://45.144.179.101/scare-me/api/mobile/v1/auth/login")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}