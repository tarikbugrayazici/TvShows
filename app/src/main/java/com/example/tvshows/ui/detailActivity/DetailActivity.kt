package com.example.tvshows.ui.detailActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshows.core.Constants
import com.example.tvshows.core.helper.DateFormatHelper
import com.example.tvshows.data.entity.DetailBaseEntity
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var viewModelDetail: DetailActivityViewModel
    private var detailTvShow: DetailBaseEntity? = null
    private var isCharacterCountOfOverviewOverflowed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tvshows.R.layout.detail_activity)
        val i = intent
        id = i.getIntExtra("id", 0)
        fetchTvDetail()
    }

    private fun fetchTvDetail() {
        viewModelDetail = ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        viewModelDetail.getDetail(id, Constants.API_KEY, Constants.LANGUAGE_CODE) {
            detailTvShow = it
            setData()
        }
    }

    private fun setData() {
        Glide.with(this)
            .load(Constants.IMAGE_BASE_PATH + detailTvShow?.backdropPath)
            .centerCrop()
            .into(detail_image)
        setGenres()

    }

    private fun setItems() {
        homepage!!.text = detailTvShow?.homepage
        homepage!!.setOnClickListener { v ->
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(detailTvShow?.homepage)
            )
            v.context.startActivity(intent)
        }
        text_original_name.text = detailTvShow?.name
        val overview = detailTvShow?.overview
        isCharacterCountOfOverviewOverflowed = overview!!.length > 140
        setOverviewText(overview)
        text_overview!!.setOnClickListener {
            if (overview!!.length > 140) {
                isCharacterCountOfOverviewOverflowed = !isCharacterCountOfOverviewOverflowed
                setOverviewText(overview)
            } else {
                text_overview!!.text = overview
            }
        }
    }

    private fun setOverviewText(overview: String?) {
        if (isCharacterCountOfOverviewOverflowed) {
            text_overview!!.text = overview!!.substring(0, 137) + "..."
        } else {
            text_overview!!.text = overview
        }
    }

    private fun setGenres() {
        var genresList = ""
        for (i in detailTvShow?.genres!!) {
            i.toString()
            genresList += i.name!! + " "
        }
        text_genres!!.text = genresList
        setEpisodes()
    }

    private fun setEpisodes() {
        last_episode_date.text = detailTvShow?.lastEpisodeToAir?.airDate?.let {
            DateFormatHelper.formatDate(
                it
            )
        }
        last_episode_name.text = detailTvShow?.lastEpisodeToAir?.name?.let {
            DateFormatHelper.formatDate(
                it
            )
        }
        next_episode_date.text = detailTvShow?.nextEpisodeToAir?.airDate?.let {
            DateFormatHelper.formatDate(
                it
            )
        }
        next_episode_name.text = detailTvShow?.nextEpisodeToAir?.name?.let {
            DateFormatHelper.formatDate(
                it
            )
        }
        setSeasons()
        setItems()
    }

    private fun setSeasons() {
        val layoutManager = LinearLayoutManager(this)
        seasons_recycler_view!!.layoutManager = layoutManager as RecyclerView.LayoutManager?
        val adapter = detailTvShow?.seasons?.let {
            SeasonsAdapter(
                this,
                it
            )
        }
        seasons_recycler_view!!.adapter = adapter
    }
}