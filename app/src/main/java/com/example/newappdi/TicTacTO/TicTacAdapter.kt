package com.example.newappdi.TicTacTO

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.example.SubCategory
import com.example.newappdi.R

class TicTacAdapter() : RecyclerView.Adapter<TicTacAdapter.TicViewHolder>() {
    class TicViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    }

    var onItemClickListener: ((MatrixModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicViewHolder {
        return TicViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tic_tac,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TicViewHolder, position: Int) {
        val article = differ.currentList[position]
        val textVIew = holder.itemView.findViewById<AppCompatButton>(R.id.btn)
        textVIew.setBackgroundColor(textVIew.context.getColor(R.color.black))
        textVIew.setText("${article.row}${article.columns}")
        if (!article.user.isNullOrEmpty()) {
            article.user?.let {
                if (it.equals("1")) {
                    textVIew.backgroundTintList =
                        ColorStateList.valueOf(textVIew.context.getColor(R.color.red))
                } else if (it.equals("2")) {
                    textVIew.backgroundTintList =
                        ColorStateList.valueOf(textVIew.context.getColor(R.color.blue))
                } else {
                    textVIew.backgroundTintList =
                        ColorStateList.valueOf(textVIew.context.getColor(R.color.black))
                }
            }
        }else {
            textVIew.backgroundTintList =
                ColorStateList.valueOf(textVIew.context.getColor(R.color.black))
        }
        textVIew.setOnClickListener {
            onItemClickListener?.invoke(article)
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<MatrixModel>() {
        override fun areItemsTheSame(oldItem: MatrixModel, newItem: MatrixModel): Boolean {
            return (oldItem.id == newItem.id && oldItem.user == newItem.user)
        }

        override fun areContentsTheSame(oldItem: MatrixModel, newItem: MatrixModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    fun setOnItemClickListner(listner: (MatrixModel) -> Unit) {
        onItemClickListener = listner
    }
}