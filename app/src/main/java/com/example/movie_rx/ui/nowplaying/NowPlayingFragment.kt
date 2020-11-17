package com.example.movie_rx.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_rx.R
import com.example.movie_rx.adapter.TopRatedAdapter
import com.example.movie_rx.model.TopratedResults

import kotlinx.android.synthetic.main.fragment_now_playing.*

import kotlinx.android.synthetic.main.fragment_top_rated.*
import java.util.ArrayList

class NowPlayingFragment : Fragment() {

    private lateinit var nowPlayingViewModel: NowPlayingViewModel
    private lateinit var toprateAdapter: TopRatedAdapter
    private var listNowPlaying: MutableList<TopratedResults> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nowPlayingViewModel =
            ViewModelProviders.of(this).get(NowPlayingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_now_playing, container, false)

        nowPlayingViewModel.getApiNowPlaying(1);
        nowPlayingViewModel.requestNowPlaying.observe(viewLifecycleOwner, Observer {
            listNowPlaying.clear()
            listNowPlaying.addAll(it)
            toprateAdapter.notifyDataSetChanged()
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toprateAdapter = TopRatedAdapter(context, listNowPlaying) {

        }
        var layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        rcv_now_playing?.adapter = toprateAdapter;
        rcv_now_playing?.layoutManager = layoutManager
    }
}
