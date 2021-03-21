package com.example.tvshows.ui.mainActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshows.R
import com.example.tvshows.core.Constants
import com.example.tvshows.core.Navigation.startDetailActivity
import com.example.tvshows.data.entity.Tv

class TvAdapter(private val context: Context, private val list: ArrayList<Tv?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
            TvViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.progress_bar, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TvViewHolder) {
            val tv = list[position]
            Glide.with(context)
                .load(Constants.IMAGE_BASE_PATH + tv!!.poster_path)
                .centerCrop()
                .into(holder.image)
            holder.itemView.setOnClickListener { startDetailActivity(context, tv.id) }
            holder.name.text = tv.name
            holder.rating.text = tv.vote_average.toString()
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    inner class TvViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val image = itemView!!.findViewById<ImageView>(R.id.img_view)
        val name = itemView!!.findViewById<TextView>(R.id.tv)
        val rating = itemView!!.findViewById<TextView>(R.id.rating)

    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar =
            itemView.findViewById<View>(com.example.tvshows.R.id.progress) as ProgressBar

    }

    private fun showLoadingView(
        viewHolder: LoadingViewHolder,
        position: Int
    ) { //ProgressBar would be displayed
    }
}