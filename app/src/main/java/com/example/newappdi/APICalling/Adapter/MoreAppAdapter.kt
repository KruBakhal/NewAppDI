package com.example.newappdi.NewsApp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.SubCategory
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.R
import com.example.newappdi.databinding.ItemArticlePreviewBinding


class MoreAppAdapter : RecyclerView.Adapter<MoreAppAdapter.NewAdapterHolder>() {
    inner class NewAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAdapterHolder {
        return NewAdapterHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    var onItemClickListener: ((SubCategory) -> Unit)? = null

    override

    fun onBindViewHolder(holder: NewAdapterHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            try {
                Glide.with(this).load(article.icon)
                    .into(holder.itemView.findViewById(R.id.ivArticleImage))
                (holder.itemView.findViewById(R.id.tvSource) as TextView).text =
                    article.name
                (holder.itemView.findViewById(R.id.tvTitle) as TextView).text = article.star
                (holder.itemView.findViewById(R.id.tvDescription) as TextView).text =
                    article.appLink


                setOnClickListener {
                    onItemClickListener?.let { it(article) }
                }
            } catch (exception: Exception) {
                Log.d("TAG", "onBindViewHolder: " + exception.message)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<SubCategory>() {
        override fun areItemsTheSame(oldItem: SubCategory, newItem: SubCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SubCategory, newItem: SubCategory): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    fun setOnItemClickListner(listner: (SubCategory) -> Unit) {
        onItemClickListener = listner
    }
}