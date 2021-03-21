package com.example.tvshows.ui.mainActivity.view

import androidx.lifecycle.ViewModel
import com.example.tvshows.data.entity.BaseEntity
import com.example.tvshows.ui.mainActivity.TvRepository

class MainActivityViewModel : ViewModel() {
    private val tvRepository = TvRepository()

    fun getTvShows(apiKey: String, language: String, pagination: Int, block: (BaseEntity) -> Unit) {
        tvRepository.getTvShows(apiKey, language, pagination) {
            block(it)
        }
    }
}