package com.example.tvshows.data.entity

import com.google.gson.annotations.SerializedName

class CreatedBy(
    @SerializedName("id") val id: Int,
    @SerializedName("credit_id") val credit_id: String,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: Int,
    @SerializedName("profile_path") val profile_path: String
)