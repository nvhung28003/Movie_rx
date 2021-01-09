package com.example.movie_rx.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_rx.BR
import com.example.movie_rx.R
import com.example.movie_rx.adapter.TopRatedAdapter
import com.example.movie_rx.databinding.DetailActivityBinding
import com.example.movie_rx.model.TopratedResults
import com.example.movie_rx.ui.toprated.TopRatedViewModel.Companion.KEY
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.fragment_top_rated.*
import java.util.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID_PAGE: String = "ID_PAGE";
    }
    private var page :Int? = null
    private var keyYoutube :String = ""
    private var checkWatchYoutube: Boolean = false
    private lateinit var toprateAdapter: TopRatedAdapter
    private var listSimilar: MutableList<TopratedResults> = ArrayList()
    private lateinit var  detailViewmodel : DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.detail_activity);
       var binding : DetailActivityBinding =
           DataBindingUtil.setContentView(this,R.layout.detail_activity);


        detailViewmodel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
         page  = intent.getIntExtra(ID_PAGE,0)
        txt_relate_movie.visibility = View.GONE
        detailViewmodel.requestAll.observe(this, Observer {
            it.run {
              //  binding.allModel = it;
                binding.setVariable(BR.allModel,it)
//                Glide.with(this@DetailActivity).load(String.format(resources.getString(R.string.display_image),
//                    it.detailModel.backdropPath)).into(imv_big)

//                Glide.with(this@DetailActivity).load(String.format(resources.getString(R.string.display_image),
//                    it.detailModel.posterPath)).into(imv_small)

            //    txt_title_detail.text = it.detailModel.title
         //       txt_over_view.text = it.detailModel.overview
                listSimilar.clear()
                listSimilar.addAll(it.similarModel.result)
                if (!listSimilar.isEmpty() && toprateAdapter != null){
                    txt_relate_movie.visibility = View.VISIBLE
                    toprateAdapter.notifyDataSetChanged()
                }
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
        initAdapterSimilar()
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

    fun initAdapterSimilar() {
        toprateAdapter = TopRatedAdapter(this, listSimilar) {
            var intent = Intent(this, DetailActivity::class.java);
            intent.putExtra(ID_PAGE, it.id)
            startActivity(intent)
        }
        var layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false);
        rcv_relate_movie?.adapter = toprateAdapter;
        rcv_relate_movie?.layoutManager = layoutManager
    }
}