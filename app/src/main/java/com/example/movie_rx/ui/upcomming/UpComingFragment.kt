package com.example.movie_rx.ui.upcomming

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
import kotlinx.android.synthetic.main.fragment_up_comming.*
import java.util.ArrayList

class UpComingFragment : Fragment() {

    private lateinit var upcommingViewModel: UpCommingViewModel
    private lateinit var toprateAdapter: TopRatedAdapter
    private var listUpComing: MutableList<TopratedResults> = ArrayList()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        upcommingViewModel =
                ViewModelProviders.of(this).get(UpCommingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_up_comming, container, false)
        upcommingViewModel.getApiUpComing(1);
        upcommingViewModel.requestUpComing.observe(viewLifecycleOwner, Observer {
                listUpComing.clear()
                listUpComing.addAll(it)
                toprateAdapter.notifyDataSetChanged()
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toprateAdapter = TopRatedAdapter(context, listUpComing) {
            var intent = Intent(context, DetailActivity::class.java);
            intent.putExtra(DetailActivity.ID_PAGE,it.id)
            startActivity(intent)
        }
        var layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        rcv_upcoming?.adapter = toprateAdapter;
        rcv_upcoming?.layoutManager = layoutManager
    }
}
