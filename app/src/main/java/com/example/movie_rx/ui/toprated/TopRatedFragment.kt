package com.example.movie_rx.ui.toprated

import android.content.Intent
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
import com.example.movie_rx.ui.detail.DetailActivity
import com.example.movie_rx.ui.detail.DetailActivity.Companion.ID_PAGE
import kotlinx.android.synthetic.main.fragment_top_rated.*
import java.util.*

class TopRatedFragment : Fragment() {

    private lateinit var topratedViewModel: TopRatedViewModel
    private lateinit var toprateAdapter: TopRatedAdapter
    private var listToprate: MutableList<TopratedResults> = ArrayList()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topratedViewModel =
            ViewModelProviders.of(this).get(TopRatedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_top_rated, container, false)
        topratedViewModel.getApiToprated(1);
        topratedViewModel.requestToprated.observe(viewLifecycleOwner, Observer {
                listToprate.clear()
                listToprate.addAll(it)
                toprateAdapter.notifyDataSetChanged()
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toprateAdapter = TopRatedAdapter(context, listToprate) {
            var intent = Intent(context,DetailActivity::class.java);
            intent.putExtra(ID_PAGE,it.id)
            startActivity(intent)
        }
        var layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        rcv_toprate?.adapter = toprateAdapter;
        rcv_toprate?.layoutManager = layoutManager
    }
}
