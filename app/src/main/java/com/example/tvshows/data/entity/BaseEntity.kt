package com.example.tvshows.data.entity

import java.util.ArrayList

data class BaseEntity(
    val page: Int = 0,
    val results: ArrayList<Tv>? = null,
    val total_pages: Int = 0,
    val total_results: Int = 0


)