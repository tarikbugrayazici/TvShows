package com.example.tvshows.ui.mainActivity

import com.example.tvshows.data.entity.BaseEntity
import com.example.tvshows.data.service.Api
import com.example.tvshows.data.service.RetrofitService
import retrofit2.Call
import retrofit2.Response

class TvRepository {

    fun getTvShows(apiKey: String, language: String, pagination: Int, block: (BaseEntity) -> Unit) {
        val retrofit: Api = RetrofitService.getRetrofit().create(Api::class.java)
        val call = retrofit.getTvShows(apiKey, language, pagination)
        call.enqueue(object : retrofit2.Callback<BaseEntity> {
            override fun onFailure(call: Call<BaseEntity>, t: Throwable) {

            }

            override fun onResponse(call: Call<BaseEntity>, response: Response<BaseEntity>) {
                response.body()?.let { block(it) }
            }
        })
    }
}