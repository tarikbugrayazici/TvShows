package com.example.tvshows.ui.detailActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshows.R
import com.example.tvshows.core.Constants
import com.example.tvshows.core.helper.DateFormatHelper
import com.example.tvshows.data.entity.Seasons
import java.util.*

class SeasonsAdapter(private val context: Context, private val list: List<Seasons>) :
    RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): SeasonsViewHolder {
        return SeasonsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_item_seasons, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        val seasons = list[position]
        holder.name.text = seasons.name
        holder.airDate.text = DateFormatHelper.formatDate(seasons.airDate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class SeasonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.season_name)
        var airDate = itemView.findViewById<TextView>(R.id.season_air_date)
    }
}