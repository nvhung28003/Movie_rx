package com.example.movie_rx.ui.popular

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
import kotlinx.android.synthetic.main.fragment_popular.*
import java.util.ArrayList

class PopularFragment : Fragment() {

    private lateinit var popularViewModel: PopularViewModel
    private lateinit var toprateAdapter: TopRatedAdapter
    private var listToprate: MutableList<TopratedResults> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        popularViewModel =
                ViewModelProviders.of(this).get(PopularViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_popular, container, false)

        popularViewModel.getApiPopular(1);
        popularViewModel.requestPopular.observe(viewLifecycleOwner, Observer {
                listToprate.clear()
                listToprate.addAll(it)
                toprateAdapter.notifyDataSetChanged()
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toprateAdapter = TopRatedAdapter(context, listToprate) {
            var intent = Intent(context, DetailActivity::class.java);
            intent.putExtra(DetailActivity.ID_PAGE,it.id)
            startActivity(intent)
        }
        var layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        rcv_popular?.adapter = toprateAdapter;
        rcv_popular?.layoutManager = layoutManager
    }
}
