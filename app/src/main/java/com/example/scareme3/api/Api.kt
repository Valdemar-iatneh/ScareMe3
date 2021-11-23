package com.example.scareme3.api

import com.example.scareme3.model.ResponseToken
import com.example.scareme3.model.Topic
import com.example.scareme3.model.User
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface Api {
    @POST("v1/auth/register")
    fun registration(@Body user: User) : Observable<Response<ResponseToken>>

    @POST("v1/auth/login")
    fun autorization(@Body user: User) : Observable<Response<ResponseToken>>

    @GET("v1/topic")
    fun getTopics(@Header("Authorization") accessToken: String) : Observable<Response<ArrayList<Topic>>>

    companion object {
        fun createApi(): Api {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://45.144.179.101/scare-me/api/mobile/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}