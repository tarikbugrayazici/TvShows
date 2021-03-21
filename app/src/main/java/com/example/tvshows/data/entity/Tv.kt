package com.example.tvshows.data.entity

import java.util.ArrayList

data class Tv(
    val backdrop_path: String?,
    val first_air_date: String?,
    val genre_ids: ArrayList<Int>? = null,
    val id: Int = 0,
    val name: String?,
    val origin_country: ArrayList<String>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Float?,
    val poster_path: String?,
    val vote_average: Float?,
    val vote_count: Int? = null
)