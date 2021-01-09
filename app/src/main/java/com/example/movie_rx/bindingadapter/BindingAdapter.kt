package com.example.movie_rx.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie_rx.R

    object BindingAdapter{
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.load)
            .error(R.drawable.load)
        Glide.with(view).load(url).apply(options).into(view)
    }
}