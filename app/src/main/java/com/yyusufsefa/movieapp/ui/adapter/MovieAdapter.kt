package com.yyusufsefa.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.data.viewstate.MovieItemViewState
import com.yyusufsefa.movieapp.databinding.ItemMovieBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(diffCallBack) {

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Movie) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder =
        MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            this.itemView.setOnClickListener { onItemClickListener?.invoke(getItem(adapterPosition)) }
        }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position))

    class MovieViewHolder(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: Movie) {
            itemMovieBinding.movieViewState = MovieItemViewState(movie)
        }
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}
