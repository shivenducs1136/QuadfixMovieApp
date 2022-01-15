package com.dsckiet.quadflix

import com.dsckiet.quadflix.model.Movies
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/shows")
    suspend fun getMovie1 (
        @Query("q") q:String
    ):Movies
    @GET("search/shows")
    suspend fun getMovie2 (
        @Query("q") q:String
    ):Movies
    @GET("search/shows")
    suspend fun getMovie3 (
        @Query("q") q:String
    ):Movies
    @GET("search/shows")
    suspend fun getMovie4 (
        @Query("q") q:String
    ):Movies
    @GET("search/shows")
    suspend fun getMovie5 (
        @Query("q") q:String
    ):Movies

}