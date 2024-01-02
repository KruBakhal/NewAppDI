package com.example.newappdi.NewsApp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newappdi.quizgame.ResultModel
import com.example.newappdi.R


class GameAdapter : RecyclerView.Adapter<GameAdapter.NewAdapterHolder>() {
    inner class NewAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewAdapterHolder {
        return NewAdapterHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_game,
                parent,
                false
            )
        )
    }

    var onItemClickListener: ((ResultModel) -> Unit)? = null

    override

    fun onBindViewHolder(holder: NewAdapterHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            try {

                (holder.itemView.findViewById(R.id.tv_1) as TextView).text = article.name
                (holder.itemView.findViewById(R.id.tv_2) as TextView).text = article.score
                (holder.itemView.findViewById(R.id.tv_3) as TextView).text = article.ans
            } catch (exception: Exception) {
                Log.d("TAG", "onBindViewHolder: " + exception.message)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<ResultModel>() {
        override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    fun setOnItemClickListner(listner: (ResultModel) -> Unit) {
        onItemClickListener = listner
    }
}