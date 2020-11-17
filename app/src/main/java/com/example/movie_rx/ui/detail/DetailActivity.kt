package com.example.movie_rx.ui.detail

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movie_rx.R
import com.example.movie_rx.ui.toprated.TopRatedViewModel.Companion.KEY
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.item_top_rated.*
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_PAGE: String = "ID_PAGE";
    }
    private var page :Int? = null
    private var keyYoutube :String = ""
    private var checkWatchYoutube: Boolean = false
    private lateinit var  detailViewmodel : DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity);
        detailViewmodel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
         page  = intent.getIntExtra(ID_PAGE,0)
        detailViewmodel.requestAll.observe(this, Observer {
            it.run {
                Glide.with(this@DetailActivity).load(String.format(resources.getString(R.string.display_image),it.detailModel.backdropPath)).into(imv_big)
                Glide.with(this@DetailActivity).load(String.format(resources.getString(R.string.display_image),it.detailModel.posterPath)).into(imv_small)
                txt_title_detail.text = it.detailModel.title
                txt_over_view.text = it.detailModel.overview
                if (it.videoModel != null &&
                    !it.videoModel.results.isEmpty() &&
                    it.videoModel.results != null &&
                    it.videoModel.results.get(0) != null &&
                       !it.videoModel.results.get(0).key.isEmpty()){
                    keyYoutube =  it.videoModel.results.get(0).key;
                    checkWatchYoutube = true
                }
            }
        })
        detailViewmodel.callDetail(page!!, KEY)
        onBackPress()
        openYoutube()
    }


    fun onBackPress() {
        imv_back.setOnClickListener { v ->
            finish();
        }
    }
    fun openYoutube() {
        imv_big.setOnClickListener { v ->
            if (checkWatchYoutube) {
                val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + keyYoutube))
                val intentBrowser = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + keyYoutube)
                )
                try {
                    this.startActivity(intentApp)
                } catch (ex: ActivityNotFoundException) {
                    this.startActivity(intentBrowser)
                }
            }
        }
    }
}