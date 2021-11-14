package com.yyusufsefa.movieapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yyusufsefa.movieapp.R
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.ui.adapter.MovieAdapter

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this).load(Constants.BASE_IMAGE_URL + imageUrl)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(this)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data.slice(4 until data.size))
}
