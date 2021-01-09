package com.example.movie_rx.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie_rx.BR


import com.example.movie_rx.R
import com.example.movie_rx.databinding.ItemTopRatedBinding
import com.example.movie_rx.model.TopratedResults
import kotlinx.android.synthetic.main.item_top_rated.view.*

class TopRatedAdapter(
    private val context: Context?, var listToprated: List<TopratedResults>
    , val callback: (toprated: TopratedResults) -> Unit
) :
    RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {


    class TopRatedViewHolder(private val itemViewBinding: ItemTopRatedBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(item : TopratedResults){
                itemViewBinding.setVariable(BR.item,item)
                itemViewBinding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val itemViewBinding = DataBindingUtil.inflate<ItemTopRatedBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_top_rated, parent, false
        )
        return TopRatedViewHolder(itemViewBinding)
    }


    override fun getItemCount(): Int {
        return listToprated.size;
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        var item: TopratedResults = listToprated.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { v -> callback.invoke(item) }
    }

}