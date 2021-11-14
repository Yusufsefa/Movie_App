package com.yyusufsefa.movieapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yyusufsefa.movieapp.data.model.Movie
import com.yyusufsefa.movieapp.data.viewstate.SliderItemViewState
import com.yyusufsefa.movieapp.databinding.ItemViewPagerBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    private var movies: List<Movie> = emptyList()

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: ((Movie) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagerViewHolder = PagerViewHolder(
        ItemViewPagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        this.itemView.setOnClickListener { onItemClickListener?.invoke(movies[adapterPosition]) }
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount(): Int = movies.size

    class PagerViewHolder(private val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(videoGame: Movie) {
            binding.sliderViewState = SliderItemViewState(videoGame)
        }
    }
}
