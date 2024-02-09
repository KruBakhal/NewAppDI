package com.example.newappdi.NewsApp.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newappdi.R
import com.example.newappdi.databinding.ItemProductItemBinding
import com.example.newappdi.tabpager.data.ProductModel


class ShopAdapter : RecyclerView.Adapter<ShopAdapter.ShopAdapterHolder>() {
    inner class ShopAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemProductItemBinding

        init {
            binding = ItemProductItemBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapterHolder {
        return ShopAdapterHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product_item,
                parent,
                false
            )
        )
    }

    var onItemClickListener: ((ProductModel) -> Unit)? = null

    override

    fun onBindViewHolder(holder: ShopAdapterHolder, position: Int) {
        val productModel = differ.currentList[position]
        holder.itemView.apply {
            try {
                holder.binding.model = productModel
                setOnClickListener {
                    onItemClickListener?.let { it(productModel) }
                }
            } catch (exception: Exception) {
                Log.d("TAG", "onBindViewHolder: " + exception.message)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    fun setOnItemClickListner(listner: (ProductModel) -> Unit) {
        onItemClickListener = listner
    }
}