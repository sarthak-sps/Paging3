package com.example.paging3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paging3.databinding.MovieslistBinding
import com.example.paging3.models.Movies

class MoviePagingAdapter: PagingDataAdapter<Movies, MoviePagingAdapter.MovieViewHolder>(MovieComparator) {
    override fun onBindViewHolder(holder: MoviePagingAdapter.MovieViewHolder, position: Int) {
         val movie= getItem(position)
        holder.view.moviename.text = movie?.original_title
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w300"+movie!!.poster_path).into(holder.view.movieBanner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePagingAdapter.MovieViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding= MovieslistBinding.inflate(inflater,parent,false)
        return MovieViewHolder(binding)
    }

    class MovieViewHolder(val view: MovieslistBinding): RecyclerView.ViewHolder(view.root) {

    }

    object MovieComparator: DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            // Id is unique.
            return oldItem.original_title == newItem.original_title
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }
    }
}