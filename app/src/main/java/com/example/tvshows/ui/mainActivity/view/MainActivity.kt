package com.example.tvshows.ui.mainActivity.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.core.Constants
import com.example.tvshows.data.entity.Tv
import com.example.tvshows.ui.mainActivity.adapter.TvAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private var isLoadingShowed = false
    var pagination = 1
    private var tvShowsList = ArrayList<Tv?>()
    var layoutManager: GridLayoutManager? = null
    private var adapter: TvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tvshows.R.layout.activity_main)
        getTvShows()
        initScrollListener()
    }

    private fun getTvShows() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getTvShows(Constants.API_KEY, Constants.LANGUAGE_CODE, pagination) {
            it.results?.let { it1 -> setData(it1) }
            if (pagination == 1) {
                setRecyclerView()
            } else {
                setLoadingCase()
            }
        }
    }

    private fun setData(tvShows: ArrayList<Tv>) {
        setLoadingCase()
        tvShowsList.addAll(tvShows)
    }

    private fun setRecyclerView() {
        layoutManager = GridLayoutManager(this, 2)
        recycler_view!!.layoutManager = layoutManager as RecyclerView.LayoutManager?
        adapter = TvAdapter(
            this,
            tvShowsList
        )
        recycler_view!!.adapter = adapter
    }

    private fun initScrollListener() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoadingShowed) {
                    if (layoutManager != null && layoutManager?.findLastCompletelyVisibleItemPosition() == tvShowsList.size - 1) {
                        loadMore()
                        isLoadingShowed = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        pagination++
        tvShowsList.add(null)
        adapter?.notifyDataSetChanged()
        getTvShows()
    }

    fun setLoadingCase() {
        if (isLoadingShowed) {
            tvShowsList.removeAt(tvShowsList.size - 1)
            adapter?.notifyDataSetChanged()
            isLoadingShowed = false
        }
    }
}