package com.example.tvshows.ui.detailActivity

import androidx.lifecycle.ViewModel
import com.example.tvshows.data.entity.DetailBaseEntity

class DetailActivityViewModel : ViewModel() {
    private val detailRepository = DetailRepository()

    fun getDetail(tvId: Int, apikey: String, language: String, block: (DetailBaseEntity) -> Unit) {
        detailRepository.getDetail(tvId, apikey, language) {
            block(it)
        }
    }
}