package com.example.movie_rx.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie_rx.R
import com.example.movie_rx.databinding.ItemTopRatedBinding
import com.example.movie_rx.model.TopratedResults
import kotlinx.android.synthetic.main.item_top_rated.view.*

class TopRatedAdapter(
    private val context: Context?, var listToprated: List<TopratedResults>
    , val callback: (toprated: TopratedResults) -> Unit
) :
    RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder>() {


    class TopRatedViewHolder(itemViewBinding: ItemTopRatedBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {


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
        holder.itemView.setOnClickListener { v -> callback.invoke(item) }
        var urlImage  = String.format(context!!.resources.getString(R.string.display_image),item.posterPath);
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.load)
            .error(R.drawable.load)

        Glide.with(context!!).load(urlImage).apply(options).into( holder.itemView.imv_top_rated)
        holder.itemView.txt_title.text = item.title;
    }

}