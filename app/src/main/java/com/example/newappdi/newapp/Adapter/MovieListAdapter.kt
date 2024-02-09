package com.example.newappdi.newapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.R
import com.example.newappdi.databinding.ItemArticlePreviewBinding

class MovieListAdapter : PagingDataAdapter<Article, MoviePosterViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        return MoviePosterViewHolder(
            ItemArticlePreviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        holder.bind(getItem(position)?.urlToImage)
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

class MoviePosterViewHolder(
    val binding: ItemArticlePreviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(path: String?) {
        path?.let {
            Glide.with(binding.ivArticleImage.context).load("$it")
                .into(binding.ivArticleImage)
        }
    }
}