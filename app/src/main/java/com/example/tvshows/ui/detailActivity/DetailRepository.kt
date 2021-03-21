package com.example.tvshows.ui.detailActivity

import android.util.Log
import com.example.tvshows.data.entity.DetailBaseEntity
import com.example.tvshows.data.service.Api
import com.example.tvshows.data.service.RetrofitService
import retrofit2.Call
import retrofit2.Response

class DetailRepository {

    fun getDetail(tvId: Int, apikey: String, language: String, block: (DetailBaseEntity) -> Unit) {
        val retrofit: Api = RetrofitService.getRetrofit().create(Api::class.java)
        val call = retrofit.getDetailofTvShow(tvId, apikey, language)
        call.enqueue(object : retrofit2.Callback<DetailBaseEntity> {
            override fun onFailure(call: Call<DetailBaseEntity>, t: Throwable) {
                Log.e("Tag", "$t")
            }

            override fun onResponse(
                call: Call<DetailBaseEntity>,
                response: Response<DetailBaseEntity>
            ) {
                response.body()?.let { block(it) }
            }
        })
    }
}