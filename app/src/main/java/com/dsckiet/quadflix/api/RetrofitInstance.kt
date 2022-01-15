package com.dsckiet.quadflix.api

import com.dsckiet.quadflix.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api : ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

}