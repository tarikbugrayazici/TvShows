package com.example.tvshows.data.service

import com.example.tvshows.data.entity.BaseEntity
import com.example.tvshows.data.entity.DetailBaseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apikey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<BaseEntity>

    @GET("tv/{tv_id}")
    fun getDetailofTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apikey: String,
        @Query("language") language: String
    ):Call<DetailBaseEntity>
}