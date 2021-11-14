package com.yyusufsefa.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.data.viewstate.SearchViewState
import com.yyusufsefa.movieapp.databinding.ItemSearchBinding

class SearchAdapter : ListAdapter<Movie, SearchAdapter.SearchViewHolder>(diffCallBack) {
    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Movie) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder =
        SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            this.itemView.setOnClickListener { onItemClickListener?.invoke(getItem(adapterPosition)) }
        }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SearchViewHolder(private val searchBinding: ItemSearchBinding) :
        RecyclerView.ViewHolder(searchBinding.root) {

        fun bind(movie: Movie) {
            searchBinding.searchViewState = SearchViewState(movie)
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
