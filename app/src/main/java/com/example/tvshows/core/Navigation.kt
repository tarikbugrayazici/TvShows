package com.example.tvshows.core

import android.content.Context
import android.content.Intent
import com.example.tvshows.ui.detailActivity.DetailActivity

object Navigation {

    fun startDetailActivity(context: Context, id: Int) {
        val i = Intent(context, DetailActivity::class.java)
        i.putExtra("id", id)
        context.startActivity(i)
    }
}